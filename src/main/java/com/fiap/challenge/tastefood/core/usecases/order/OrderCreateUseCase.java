package com.fiap.challenge.tastefood.core.usecases.order;

import com.fiap.challenge.tastefood.core.common.validator.OrderCreateValidator;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.OrderProduct;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import com.fiap.challenge.tastefood.core.gateways.ProductGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderCreateUseCase {

    private final ProductGateway productGateway;
    private final OrderGateway orderGateway;
    private final OrderCreateValidator orderCreateValidator;

    public Order execute(Order order) {

        orderCreateValidator.validate(order);

        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.CRIADO);
        order.setTotal(BigDecimal.ZERO);

        for (OrderProduct orderProduct : order.getProducts()) {
            orderProduct.setProduct(productGateway.findById(orderProduct.getProduct().getId()).orElseThrow());
            orderProduct.setPrice(orderProduct.getProduct().getPrice());
            order.setTotal(order.getTotal().add(BigDecimal.valueOf(orderProduct.getQuantity()).multiply(orderProduct.getPrice())));
        }

        return orderGateway.save(order);
    }

}
