package com.fiap.challenge.tastefood.core.applications.useCases.order;

import com.fiap.challenge.tastefood.adapter.driven.infra.OrderGateway;
import com.fiap.challenge.tastefood.core.applications.dtos.Order;
import com.fiap.challenge.tastefood.core.domain.entities.OrderEntity;
import com.fiap.challenge.tastefood.core.domain.entities.StatusOrderEnum;
import com.fiap.challenge.tastefood.core.domain.exception.OrderException;
import com.fiap.challenge.tastefood.core.domain.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class CreateOrderUseCase {

    private final OrderGateway gateway;
    private final OrderMapper orderMapper;

    @Autowired
    public CreateOrderUseCase(OrderGateway orderGateway,
                              OrderMapper orderMapper) {
        this.gateway = orderGateway;
        this.orderMapper = orderMapper;
    }

    public Long execute(Order order) throws OrderException {
        if (order.getProducts().isEmpty()) {
            throw new OrderException("Pedido não pode ser criado sem produtos selecionados!");
        } else {
            OrderEntity orderEntity = this.orderMapper.toOrderEntity(order);
            orderEntity.setStatusOrderEnum(StatusOrderEnum.RECEBIDO);
            return gateway.create(orderEntity);
        }
    }

}
