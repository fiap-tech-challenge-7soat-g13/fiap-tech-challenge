package com.fiap.challenge.tastefood.core.usecases.order;

import com.fiap.challenge.tastefood.core.common.exception.EntityNotFoundException;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderGetUseCaseTest {

    private final OrderGateway orderGateway = mock(OrderGateway.class);

    private final OrderGetUseCase orderGetUseCase = new OrderGetUseCase(orderGateway);

    @Test
    void shouldReturnOrder() {

        Long id = 1L;

        Order order = new Order();

        when(orderGateway.findById(id)).thenReturn(Optional.of(order));

        Order actual = orderGetUseCase.execute(id);

        assertEquals(order, actual);
    }

    @Test
    void shouldThrow() {

        Long id = 1L;

        when(orderGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> orderGetUseCase.execute(id));
    }

}