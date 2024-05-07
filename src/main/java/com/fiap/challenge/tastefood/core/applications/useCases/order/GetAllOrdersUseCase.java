package com.fiap.challenge.tastefood.core.applications.useCases.order;

import com.fiap.challenge.tastefood.core.applications.dtos.Order;
import com.fiap.challenge.tastefood.adapter.driven.infra.OrderGateway;
import com.fiap.challenge.tastefood.core.domain.mapper.OrderMapper;
import com.fiap.challenge.tastefood.core.domain.entities.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GetAllOrdersUseCase {

    private final OrderGateway gateway;
    private final OrderMapper orderMapper;

    @Autowired
    public GetAllOrdersUseCase(OrderGateway orderGateway,
                               OrderMapper orderMapper) {
        this.gateway = orderGateway;
	    this.orderMapper = orderMapper;
    }

    public List<Order> execute() {
        List<OrderEntity> orders = gateway.findAll();
        if (!orders.isEmpty()) {
            return orders.stream().map(this.orderMapper::fromOrderEntity).toList();
        }
        log.info("Não há pedidos!");
        return List.of();
    }

}
