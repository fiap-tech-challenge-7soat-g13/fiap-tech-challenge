package com.fiap.challenge.tastefood.core.useCases.order;

import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderListUseCase {

    private final OrderGateway orderGateway;

    public List<Order> execute(OrderStatus status) {
        return status == null ? orderGateway.findAll() : orderGateway.findByStatus(status);
    }

}
