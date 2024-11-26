package com.fiap.challenge.tastefood.app.adapter.input.queue.payment;

import com.fiap.challenge.tastefood.app.adapter.input.queue.payment.dto.PaymentStatusChangedEvent;
import com.fiap.challenge.tastefood.app.adapter.input.queue.payment.mapper.PaymentStatusChangedEventMapper;
import com.fiap.challenge.tastefood.core.domain.Payment;
import com.fiap.challenge.tastefood.core.usecases.payment.PaymentVerifyUseCase;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class PaymentStatusChangedEventConsumerTest {

    private final PaymentStatusChangedEventMapper paymentStatusChangedEventMapper = mock(PaymentStatusChangedEventMapper.class);
    private final PaymentVerifyUseCase paymentVerifyUseCase = mock(PaymentVerifyUseCase.class);

    private final PaymentStatusChangedEventConsumer paymentStatusChangedEventConsumer = new PaymentStatusChangedEventConsumer(paymentStatusChangedEventMapper, paymentVerifyUseCase);

    @Test
    void shouldConsume() {

        PaymentStatusChangedEvent event = new PaymentStatusChangedEvent();
        Payment payment = new Payment();

        when(paymentStatusChangedEventMapper.toPayment(event)).thenReturn(payment);

        paymentStatusChangedEventConsumer.consume(event);

        verify(paymentStatusChangedEventMapper).toPayment(event);
        verify(paymentVerifyUseCase).execute(payment);
    }


}