package com.matheus.notificacao.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmMQConfiguration {

    @Bean
    public MessageConverter Jackson2JsonMessageConverter () {
        return new Jackson2JsonMessageConverter();
    }
}
