package com.fiap.challenge.tastefood.core.usecases.order;

import com.fiap.challenge.tastefood.core.common.validator.OrderUpdateStatusValidator;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderUpdateStatusUseCase {

    private final OrderGateway orderGateway;

    private final OrderUpdateStatusValidator validator;

    public Order execute(Long id, OrderStatus status) {
        validator.validate(id, status);

        Order order = orderGateway.findById(id).orElseThrow();
        order.setStatus(status);
        return orderGateway.save(order);
    }

}
