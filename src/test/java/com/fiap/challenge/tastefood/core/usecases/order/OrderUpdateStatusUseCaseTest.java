package com.fiap.challenge.tastefood.core.usecases.order;

import com.fiap.challenge.tastefood.core.common.validator.OrderUpdateStatusValidator;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderUpdateStatusUseCaseTest {

    private final OrderGateway orderGateway = mock(OrderGateway.class);

    private final OrderUpdateStatusValidator validator = mock(OrderUpdateStatusValidator.class);

    private final OrderUpdateStatusUseCase orderUpdateStatusUseCase = new OrderUpdateStatusUseCase(orderGateway, validator);

    @Test
    void shouldUpdateOrderStatus() {

        Long id = 1L;
        OrderStatus status = OrderStatus.PRONTO;

        Order order = new Order();

        when(orderGateway.findById(id)).thenReturn(Optional.of(order));

        orderUpdateStatusUseCase.execute(id, status);

        verify(orderGateway).save(order);

        assertEquals(status, order.getStatus());
    }

}