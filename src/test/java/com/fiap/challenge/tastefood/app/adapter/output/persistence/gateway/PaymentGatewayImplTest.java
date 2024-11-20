package com.fiap.challenge.tastefood.app.adapter.output.persistence.gateway;

import com.fiap.challenge.tastefood.app.adapter.output.externalapis.PaymentClient;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.PaymentEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper.PaymentMapper;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.repository.PaymentRepository;
import com.fiap.challenge.tastefood.core.domain.Payment;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentGatewayImplTest {

    private final PaymentRepository paymentRepository = mock(PaymentRepository.class);
    private final PaymentClient paymentClient = mock(PaymentClient.class);
    private final PaymentMapper paymentMapper = mock(PaymentMapper.class);

    private final PaymentGatewayImpl paymentGateway = new PaymentGatewayImpl(paymentRepository, paymentClient, paymentMapper);

    @Test
    void shouldFindByOrderId() {

        Long orderId = 1L;

        PaymentEntity paymentEntity = new PaymentEntity();
        Payment payment = new Payment();

        when(paymentRepository.findByOrderId(orderId)).thenReturn(Optional.of(paymentEntity));
        when(paymentMapper.toPayment(paymentEntity)).thenReturn(payment);

        Optional<Payment> actual = paymentGateway.findByOrderId(orderId);

        verify(paymentRepository).findByOrderId(orderId);
        verify(paymentMapper).toPayment(paymentEntity);

        assertEquals(Optional.of(payment), actual);
    }

}
