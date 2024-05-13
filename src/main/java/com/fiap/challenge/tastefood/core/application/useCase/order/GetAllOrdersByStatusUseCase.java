package com.fiap.challenge.tastefood.core.application.useCase.order;

import com.fiap.challenge.tastefood.core.application.dto.Order;
import com.fiap.challenge.tastefood.adapter.driver.infra.OrderGateway;
import com.fiap.challenge.tastefood.core.domain.entity.OrderStatusEnum;
import com.fiap.challenge.tastefood.core.domain.mapper.OrderMapper;
import com.fiap.challenge.tastefood.core.domain.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class GetAllOrdersByStatusUseCase {

    private final OrderGateway gateway;
    private final OrderMapper orderMapper;

    public List<Order> execute(String status) {
        OrderStatusEnum orderStatusEnum = OrderStatusEnum.valueOf(status.toUpperCase());
        List<OrderEntity> orders = gateway.findByStatus(orderStatusEnum);
        if (!orders.isEmpty()) {
            return orders.stream().map(this.orderMapper::fromOrderEntity).toList();
        }
        log.info("Não há pedidos neste status: {}", status);
        return List.of();
    }

}
