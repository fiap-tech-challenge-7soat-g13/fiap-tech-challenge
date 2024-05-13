package com.fiap.challenge.tastefood.core.application.useCase.order;

import com.fiap.challenge.tastefood.core.application.dto.Order;
import com.fiap.challenge.tastefood.adapter.driver.infra.OrderGateway;
import com.fiap.challenge.tastefood.core.domain.mapper.OrderMapper;
import com.fiap.challenge.tastefood.core.domain.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class GetAllOrdersUseCase {

    private final OrderGateway gateway;
    private final OrderMapper orderMapper;

    public List<Order> execute() {
        List<OrderEntity> orders = gateway.findAll();
        if (!orders.isEmpty()) {
            return orders.stream().map(this.orderMapper::fromOrderEntity).toList();
        }
        log.info("Não há pedidos!");
        return List.of();
    }

}
