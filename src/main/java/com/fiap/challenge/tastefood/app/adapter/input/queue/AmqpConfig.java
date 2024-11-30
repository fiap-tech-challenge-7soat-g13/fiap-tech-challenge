package com.fiap.challenge.tastefood.app.adapter.input.queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    private final String paymentStatusChangedQueue;

    public AmqpConfig(@Value("${application.queue.paymentStatusChanged.name}") String paymentStatusChangedQueue) {
        this.paymentStatusChangedQueue = paymentStatusChangedQueue;
    }

    @Bean
    public MessageConverter converter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public Queue paymentStatusChangedQueue() {
        return new Queue(paymentStatusChangedQueue);
    }

}
