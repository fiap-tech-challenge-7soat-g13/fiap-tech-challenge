package com.fiap.challenge.tastefood.core.usecases.order;

import com.fiap.challenge.tastefood.core.common.validator.OrderCheckoutValidator;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.Payment;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import com.fiap.challenge.tastefood.core.gateways.PaymentGateway;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class OrderCheckoutUseCaseTest {

    private final OrderGateway orderGateway = mock(OrderGateway.class);

    private final PaymentGateway paymentGateway = mock(PaymentGateway.class);

    private final OrderCheckoutValidator orderCheckoutValidator = mock(OrderCheckoutValidator.class);

    private final OrderCheckoutUseCase orderCheckoutUseCase = new OrderCheckoutUseCase(orderGateway, paymentGateway, orderCheckoutValidator);

    @Test
    void shouldCreatePayment() {

        Long id = 1L;
        String qrCode = "GeneratedQrCode";

        Order order = new Order();

        when(orderGateway.findById(id)).thenReturn(Optional.of(order));
        when(paymentGateway.generatePayment(any(), eq(order))).thenReturn(qrCode);
        when(paymentGateway.save(any())).then(returnsFirstArg());

        orderCheckoutUseCase.execute(id);

        Payment payment = order.getPayment();

        verify(paymentGateway).save(payment);
        verify(orderGateway).save(order);

        assertEquals(qrCode, payment.getQrCode());
        assertEquals(PaymentStatus.PENDENTE, payment.getStatus());
        assertEquals(order, payment.getOrder());
        assertEquals(OrderStatus.RECEBIDO, order.getStatus());
    }

}