package com.fiap.challenge.tastefood.core.applications.useCases.order;

import com.fiap.challenge.tastefood.adapter.driven.infra.OrderGateway;
import com.fiap.challenge.tastefood.core.applications.dtos.Order;
import com.fiap.challenge.tastefood.core.applications.dtos.StatusOrderEnum;
import com.fiap.challenge.tastefood.core.domain.entities.OrderEntity;
import com.fiap.challenge.tastefood.core.domain.exception.OrderException;
import com.fiap.challenge.tastefood.core.domain.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UpdateStatusOrderUseCase {

    private final OrderGateway gateway;
    private final OrderMapper orderMapper;

    @Autowired
    public UpdateStatusOrderUseCase(OrderGateway orderGateway,
                                    OrderMapper orderMapper) {
        this.gateway = orderGateway;
	    this.orderMapper = orderMapper;
    }

    public Order execute(Order order) throws OrderException {
        try {
            StatusOrderEnum statusOrderEnum = order.getStatusOrderEnum();
            OrderEntity orderEntity = gateway.findbyId(order.getId());
            if(orderEntity.getId() == null)
                throw new OrderException(String.format("Pedido informado não existe: %s!", order.getId()));

            StatusOrderEnum.isValidUpdateStatus(StatusOrderEnum.getById(orderEntity.getStatusOrderEnum().getName()), statusOrderEnum);

            return this.orderMapper.fromOrderEntity(orderEntity);
        } catch (Exception e) {
            log.error("Erro ao atualizar status do pedido: " + e.getMessage());
            throw new OrderException(e.getMessage());
        }
    }

}
