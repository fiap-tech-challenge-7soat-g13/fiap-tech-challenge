package com.fiap.challenge.tastefood.core.usecases.order;

import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderQueueListUseCaseTest {

    private final OrderGateway orderGateway = mock(OrderGateway.class);

    private final OrderQueueListUseCase orderQueueListUseCase = new OrderQueueListUseCase(orderGateway);

    @Test
    void shouldListAll() {

        List<String> statuses = List.of(OrderStatus.PRONTO.name());
        List<Order> expected = List.of(new Order(), new Order());

        when(orderGateway.findAllByStatusInOrderByStatusDesc(statuses)).thenReturn(expected);

        List<Order> actual = orderQueueListUseCase.execute(statuses);

        verify(orderGateway).findAllByStatusInOrderByStatusDesc(statuses);

        assertEquals(expected, actual);
    }

}