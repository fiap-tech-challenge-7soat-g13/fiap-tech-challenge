package com.fiap.challenge.tastefood.core.useCases.order;

import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.enums.OrderPaymentStatus;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderCheckoutUseCase {

    private final OrderGateway orderGateway;

    public Order execute(Long id) {
        Order order = orderGateway.findById(id).orElseThrow();
        order.setStatus(OrderStatus.RECEBIDO);
        order.setPaymentStatus(OrderPaymentStatus.PENDENTE);
        return orderGateway.save(order);
    }

}
