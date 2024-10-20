package com.fiap.challenge.tastefood.core.usecases.order;

import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderListUseCaseTest {

    private final OrderGateway orderGateway = mock(OrderGateway.class);

    private final OrderListUseCase orderListUseCase = new OrderListUseCase(orderGateway);

    @Test
    void shouldListAll() {

        List<Order> expected = List.of(new Order(), new Order());

        when(orderGateway.findAll()).thenReturn(expected);

        List<Order> actual = orderListUseCase.execute(null);

        verify(orderGateway).findAll();
        verify(orderGateway, never()).findByStatus(any());

        assertEquals(expected, actual);
    }

    @Test
    void shouldListByCategory() {

        OrderStatus status = OrderStatus.PRONTO;
        List<Order> expected = List.of(new Order(), new Order());

        when(orderGateway.findByStatus(status)).thenReturn(expected);

        List<Order> actual = orderListUseCase.execute(status);

        verify(orderGateway).findByStatus(any());
        verify(orderGateway, never()).findAll();

        assertEquals(expected, actual);
    }

}