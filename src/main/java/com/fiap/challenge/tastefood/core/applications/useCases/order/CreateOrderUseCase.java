package com.fiap.challenge.tastefood.core.applications.useCases.order;

import com.fiap.challenge.tastefood.adapter.driven.infra.OrderGateway;
import com.fiap.challenge.tastefood.core.applications.dtos.Order;
import com.fiap.challenge.tastefood.core.domain.entities.OrderEntity;
import com.fiap.challenge.tastefood.core.domain.entities.OrderStatusEnum;
import com.fiap.challenge.tastefood.core.domain.exception.InvalidDataException;
import com.fiap.challenge.tastefood.core.domain.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class CreateOrderUseCase {

    private final OrderGateway gateway;
    private final OrderMapper orderMapper;

    public Long execute(Order order) throws InvalidDataException {
        if (order.getProducts().isEmpty()) {
            throw new InvalidDataException("Pedido não pode ser criado sem produtos selecionados!");
        } else {
            OrderEntity orderEntity = this.orderMapper.toOrderEntity(order);
            orderEntity.setStatus(OrderStatusEnum.RECEBIDO);
            return gateway.create(orderEntity);
        }
    }

}
