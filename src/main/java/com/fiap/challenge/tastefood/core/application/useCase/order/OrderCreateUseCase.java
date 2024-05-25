package com.fiap.challenge.tastefood.core.application.useCase.order;

import com.fiap.challenge.tastefood.core.domain.entity.Order;
import com.fiap.challenge.tastefood.core.domain.entity.OrderProduct;
import com.fiap.challenge.tastefood.core.domain.valueObject.OrderStatus;
import com.fiap.challenge.tastefood.core.domain.repository.OrderRepository;
import com.fiap.challenge.tastefood.core.domain.validation.OrderCreateValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderCreateUseCase {

    private final OrderRepository repository;

    private final OrderCreateValidator validator;

    @Transactional
    public Long execute(Order order) {

        validator.validate(order);

        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.RECEBIDO);
        order.setTotal(BigDecimal.ZERO);

        for (OrderProduct orderProduct : order.getProducts()) {
            orderProduct.setOrder(order);
            orderProduct.setPrice(orderProduct.getProduct().getPrice());
            order.setTotal(order.getTotal().add(BigDecimal.valueOf(orderProduct.getQuantity()).multiply(orderProduct.getPrice())));
        }

        Order saved = repository.save(order);

        return saved.getId();
    }

}
