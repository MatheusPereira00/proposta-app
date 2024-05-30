package com.proposta.loan.service;

import com.proposta.loan.dto.PropostaRequestDto;
import com.proposta.loan.dto.PropostaResponseDto;
import com.proposta.loan.entity.Proposta;
import com.proposta.loan.mapper.PropostaMapper;
import com.proposta.loan.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    private PropostaMapper propostaMapper;
    private PropostaRepository propostaRepository;
    private NotificacaoService notificacaoService;
    private String exchange;

    public PropostaService(PropostaMapper propostaMapper,
                           PropostaRepository propostaRepository,
                           NotificacaoService notificacaoService,
                           @Value("${rabbitmq.propostapendente.exchange}") String exchange)
    {
        this.propostaMapper = propostaMapper;
        this.propostaRepository = propostaRepository;
        this.notificacaoService = notificacaoService;
        this.exchange = exchange;
    }

    public PropostaResponseDto criar(PropostaRequestDto propostaRequestDto){
        Proposta proposta = propostaMapper.convertDtoToProposta(propostaRequestDto);
        propostaRepository.save(proposta);
        notificarRabbitMQ(proposta);
        return propostaMapper.convertPropostaToDto(proposta);
    }

    public void notificarRabbitMQ(Proposta proposta) {
        try {
            notificacaoService.notificarExchange(proposta, exchange);
        } catch (RuntimeException e) {
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }
    }

    public List<PropostaResponseDto> obterProposta(){
        return propostaMapper.convertListEntityToListDto(propostaRepository.findAll());
    }
}
