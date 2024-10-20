package com.fiap.challenge.tastefood.core.usecases.payment;

import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.Payment;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import com.fiap.challenge.tastefood.core.gateways.PaymentGateway;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentVerifyUseCaseTest {

    private final OrderGateway orderGateway = mock(OrderGateway.class);

    private final PaymentGateway paymentGateway = mock(PaymentGateway.class);

    private final PaymentVerifyUseCase paymentVerifyUseCase = new PaymentVerifyUseCase(orderGateway, paymentGateway);

    @Test
    void shouldNotSaveAnything() {

        String id = "1";
        UUID uuid = UUID.randomUUID();

        Payment payment = new Payment();

        payment.setStatus(PaymentStatus.FALHADO);

        when(paymentGateway.findByUuid(uuid)).thenReturn(Optional.of(payment));

        paymentVerifyUseCase.execute(uuid, id);

        verify(paymentGateway, never()).save(any());
        verify(paymentGateway, never()).verifyPayment(any());
        verify(orderGateway, never()).save(any());
    }

    @Test
    void shouldSetExternalId() {

        String id = "1";
        UUID uuid = UUID.randomUUID();

        Payment payment = new Payment();

        payment.setStatus(PaymentStatus.PENDENTE);

        when(paymentGateway.findByUuid(uuid)).thenReturn(Optional.of(payment));
        when(paymentGateway.verifyPayment(payment)).thenReturn(PaymentStatus.PENDENTE);

        paymentVerifyUseCase.execute(uuid, id);

        verify(paymentGateway).save(payment);

        assertEquals(payment.getExternalId(), id);
    }

    @Test
    void shouldUpdatePaymentAndOrder() {

        String id = "1";
        UUID uuid = UUID.randomUUID();

        Order order = new Order();

        Payment payment = new Payment();

        payment.setExternalId(id);
        payment.setStatus(PaymentStatus.PENDENTE);
        payment.setOrder(order);

        when(paymentGateway.findByUuid(uuid)).thenReturn(Optional.of(payment));
        when(paymentGateway.verifyPayment(payment)).thenReturn(PaymentStatus.APROVADO);

        paymentVerifyUseCase.execute(uuid, id);

        verify(paymentGateway).save(payment);
        verify(orderGateway).save(order);

        assertEquals(PaymentStatus.APROVADO, payment.getStatus());
        assertEquals(OrderStatus.EM_PREPARACAO, order.getStatus());
    }

}