package com.fiap.challenge.tastefood.core.useCases.order;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.OrderEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.OrderProductEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper.OrderMapper;
import com.fiap.challenge.tastefood.core.common.validator.OrderCreateValidator;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderCreateUseCase {

    private final OrderMapper mapper;
    private final OrderGateway orderGateway;
    private final OrderCreateValidator validator;

    @Transactional
    public Long execute(Order order) {

        validator.validate(order);

        OrderEntity entity = mapper.toOrderEntity(order);

        entity.setCreatedAt(LocalDateTime.now());
        entity.setStatus(OrderStatus.CRIADO);
        entity.setTotal(BigDecimal.ZERO);

        for (OrderProductEntity orderProductEntity : entity.getProducts()) {
            orderProductEntity.setOrder(entity);
            orderProductEntity.setPrice(orderProductEntity.getProduct().getPrice());
            entity.setTotal(entity.getTotal().add(BigDecimal.valueOf(orderProductEntity.getQuantity()).multiply(orderProductEntity.getPrice())));
        }

        OrderEntity saved = orderGateway.save(entity);

        return saved.getId();
    }

}
