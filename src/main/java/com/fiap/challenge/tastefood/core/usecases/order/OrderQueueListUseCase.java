package com.fiap.challenge.tastefood.core.usecases.order;

import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderQueueListUseCase {

    private final OrderGateway orderGateway;

    public List<Order> execute(List<OrderStatus> orderStatus) {
        return orderGateway.findAllByStatusInOrderByStatusDesc(orderStatus);
    }

}
