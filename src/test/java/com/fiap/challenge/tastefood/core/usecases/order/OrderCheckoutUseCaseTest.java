package com.fiap.challenge.tastefood.core.usecases.order;

import com.fiap.challenge.tastefood.app.adapter.output.externalapis.PaymentClient;
import com.fiap.challenge.tastefood.core.common.validator.OrderCheckoutValidator;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.Payment;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderCheckoutUseCaseTest {

    private final OrderGateway orderGateway = mock(OrderGateway.class);

    private final PaymentClient paymentClient = mock(PaymentClient.class);

    private final OrderCheckoutValidator orderCheckoutValidator = mock(OrderCheckoutValidator.class);

    private final OrderCheckoutUseCase orderCheckoutUseCase = new OrderCheckoutUseCase(orderGateway, paymentClient, orderCheckoutValidator);

    @Test
    void shouldCreatePayment() {

        Long id = 1L;

        Order order = new Order();
        Order savedOrder = new Order();
        Payment payment = new Payment();

        when(orderGateway.findById(id)).thenReturn(Optional.of(order));
        when(paymentClient.createPayment(order)).thenReturn(payment);
        when(orderGateway.save(order)).thenReturn(savedOrder);

        Order actual = orderCheckoutUseCase.execute(id);

        ArgumentCaptor<Order> orderArgument = ArgumentCaptor.forClass(Order.class);

        verify(orderGateway).findById(id);
        verify(paymentClient).createPayment(order);
        verify(orderGateway).save(orderArgument.capture());

        assertEquals(savedOrder, actual);
        assertEquals(OrderStatus.RECEBIDO, orderArgument.getValue().getStatus());
        assertEquals(payment, orderArgument.getValue().getPayment());
    }

}