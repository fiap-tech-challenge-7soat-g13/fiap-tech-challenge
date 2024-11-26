package com.fiap.challenge.tastefood.core.usecases.order;

import com.fiap.challenge.tastefood.app.adapter.output.externalapis.PaymentClient;
import com.fiap.challenge.tastefood.core.common.validator.OrderCheckoutValidator;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderCheckoutUseCase {

    private final OrderGateway orderGateway;
    private final PaymentClient paymentClient;
    private final OrderCheckoutValidator orderCheckoutValidator;

    public Order execute(Long id) {

        orderCheckoutValidator.validate(id);

        Order order = orderGateway.findById(id).orElseThrow();

        order.setStatus(OrderStatus.RECEBIDO);
        order.setPayment(paymentClient.createPayment(order));

        return orderGateway.save(order);
    }

}
