package com.proposta.loan.service;

import com.proposta.loan.entity.Proposta;
import com.proposta.loan.mapper.PropostaMapper;
import com.proposta.loan.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class PropostaSemIntegracao {

    private final PropostaRepository propostaRepository;
    private final NotificacaoService notificacaoService;
    private final String exchange;

    public PropostaSemIntegracao(PropostaMapper propostaMapper,
                           PropostaRepository propostaRepository,
                           NotificacaoService notificacaoService,
                           @Value("${rabbitmq.propostapendente.exchange}") String exchange)
    {
        this.propostaRepository = propostaRepository;
        this.notificacaoService = notificacaoService;
        this.exchange = exchange;
    }

    @Scheduled(fixedDelay = 10 , timeUnit = TimeUnit.SECONDS)
    public void buscarPropostasSemIntegracao(){
        propostaRepository.findByIntegradaIsFalse().forEach(proposta -> {
            try {
                notificacaoService.notificarExchange(proposta, exchange);
                atualizarProposta(proposta);
            } catch (RuntimeException e){
                System.out.println("error ao tentar atualizar proposta");
            }
        });
    }

    public String atualizarProposta(Proposta proposta){
        proposta.setIntegrada(true);
        propostaRepository.save(proposta);
        return "atualizada com sucesso";
    }
}


