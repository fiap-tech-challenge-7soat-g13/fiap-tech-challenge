package com.fiap.challenge.tastefood.core.usecases.payment;

import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.Payment;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentVerifyUseCaseTest {

    private final OrderGateway orderGateway = mock(OrderGateway.class);

    private final PaymentVerifyUseCase paymentVerifyUseCase = new PaymentVerifyUseCase(orderGateway);

    @Test
    void shouldUpdateOrder() {

        Payment payment = new Payment();

        payment.setId(1L);
        payment.setStatus(PaymentStatus.APROVADO);

        Order order = new Order();

        when(orderGateway.findByPaymentId(payment.getId())).thenReturn(order);

        paymentVerifyUseCase.execute(payment);

        verify(orderGateway).save(order);

        assertEquals(OrderStatus.EM_PREPARACAO, order.getStatus());
    }

}