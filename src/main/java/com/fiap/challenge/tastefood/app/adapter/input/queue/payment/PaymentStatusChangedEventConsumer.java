package com.fiap.challenge.tastefood.app.adapter.input.queue.payment;

import com.fiap.challenge.tastefood.app.adapter.input.queue.payment.dto.PaymentStatusChangedEvent;
import com.fiap.challenge.tastefood.app.adapter.input.queue.payment.mapper.PaymentStatusChangedEventMapper;
import com.fiap.challenge.tastefood.core.domain.Payment;
import com.fiap.challenge.tastefood.core.usecases.payment.PaymentVerifyUseCase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PaymentStatusChangedEventConsumer {

    private final PaymentStatusChangedEventMapper paymentStatusChangedEventMapper;
    private final PaymentVerifyUseCase paymentVerifyUseCase;

    @Transactional
    @RabbitListener(queues = "${application.queue.payment-status-changed.name}")
    public void consume(PaymentStatusChangedEvent event) {
        Payment payment = paymentStatusChangedEventMapper.toPayment(event);
        paymentVerifyUseCase.execute(payment);
    }

}
