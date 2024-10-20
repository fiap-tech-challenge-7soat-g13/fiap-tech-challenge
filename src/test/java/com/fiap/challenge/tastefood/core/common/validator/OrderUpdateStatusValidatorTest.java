package com.fiap.challenge.tastefood.core.common.validator;

import com.fiap.challenge.tastefood.core.common.exception.InvalidDataException;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderUpdateStatusValidatorTest {

    private final OrderGateway orderGateway = mock(OrderGateway.class);

    private final OrderUpdateStatusValidator orderUpdateStatusValidator = new OrderUpdateStatusValidator(orderGateway);

    @Test
    void shouldDoesNotThrow() {

        Long id = 1L;
        OrderStatus status = OrderStatus.RECEBIDO;

        Order order = new Order();

        order.setStatus(OrderStatus.CRIADO);

        when(orderGateway.findById(id)).thenReturn(Optional.of(order));

        assertDoesNotThrow(() -> orderUpdateStatusValidator.validate(id, status));
    }

    @Test
    void shouldThrow() {

        Long id = 1L;
        OrderStatus status = OrderStatus.FINALIZADO;

        Order order = new Order();

        order.setStatus(OrderStatus.CRIADO);

        when(orderGateway.findById(id)).thenReturn(Optional.of(order));

        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> orderUpdateStatusValidator.validate(id, status));

        assertEquals(List.of("O novo status do pedido é inválido"), exception.getMessages());
    }

}