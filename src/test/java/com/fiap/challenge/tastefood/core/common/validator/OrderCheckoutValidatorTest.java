package com.fiap.challenge.tastefood.core.common.validator;

import com.fiap.challenge.tastefood.core.common.exception.EntityNotFoundException;
import com.fiap.challenge.tastefood.core.common.exception.InvalidDataException;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class OrderCheckoutValidatorTest {

    private final OrderGateway orderGateway = mock(OrderGateway.class);

    private final OrderCheckoutValidator orderCheckoutValidator = new OrderCheckoutValidator(orderGateway);

    @Test
    void shouldNotThrow() {

        Long id = 1L;

        Order order = new Order();

        order.setStatus(OrderStatus.CRIADO);

        when(orderGateway.findById(id)).thenReturn(Optional.of(order));

        orderCheckoutValidator.validate(id);

        verify(orderGateway).findById(id);
    }

    @Test
    void shouldThrowInvalidDataException() {

        Long id = 1L;

        Order order = new Order();

        order.setStatus(OrderStatus.RECEBIDO);

        when(orderGateway.findById(id)).thenReturn(Optional.of(order));

        InvalidDataException exception = assertThrows(InvalidDataException.class, () -> orderCheckoutValidator.validate(id));

        verify(orderGateway).findById(id);

        assertEquals(List.of("Só é possível realizar o checkout em pedidos com o status CRIADO"), exception.getMessages());
    }

    @Test
    void shouldThrowNotFound() {

        Long id = 1L;

        when(orderGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> orderCheckoutValidator.validate(id));

        verify(orderGateway).findById(id);
    }

}