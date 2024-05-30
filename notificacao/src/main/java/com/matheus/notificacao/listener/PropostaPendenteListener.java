package com.matheus.notificacao.listener;

import com.matheus.notificacao.domain.Proposta;
import com.matheus.notificacao.service.NotificacaoSnsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.matheus.notificacao.constante.MensagemConstante.PROPOSTA_EM_ANALISE;

@Component
public class PropostaPendenteListener {

    private NotificacaoSnsService notificacaoSnsService;

    public PropostaPendenteListener(NotificacaoSnsService notificacaoSnsService) {
        this.notificacaoSnsService = notificacaoSnsService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void propostaPendente(Proposta proposta){
        String mensagem = String.format(PROPOSTA_EM_ANALISE, proposta.getUsuario().getNome());
        notificacaoSnsService.notificar(mensagem);
        System.out.println(mensagem);
    }
}
