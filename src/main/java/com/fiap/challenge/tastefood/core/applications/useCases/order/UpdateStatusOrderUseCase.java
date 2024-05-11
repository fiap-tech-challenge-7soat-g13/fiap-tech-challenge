package com.fiap.challenge.tastefood.core.applications.useCases.order;

import com.fiap.challenge.tastefood.adapter.driven.infra.OrderGateway;
import com.fiap.challenge.tastefood.core.applications.dtos.Order;
import com.fiap.challenge.tastefood.core.applications.dtos.StatusOrderEnum;
import com.fiap.challenge.tastefood.core.domain.entities.OrderEntity;
import com.fiap.challenge.tastefood.core.domain.exception.OrderException;
import com.fiap.challenge.tastefood.core.domain.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UpdateStatusOrderUseCase {

    private final OrderGateway gateway;
    private final OrderMapper orderMapper;

    public Order execute(Order order) throws OrderException {
        try {
            StatusOrderEnum statusOrderEnum = order.getStatus();
            OrderEntity orderEntity = gateway.findbyId(order.getId());
            if(orderEntity.getId() == null)
                throw new OrderException(String.format("Pedido informado não existe: %s!", order.getId()));

            StatusOrderEnum.isValidUpdateStatus(StatusOrderEnum.getById(orderEntity.getStatus().getName()), statusOrderEnum);

            return this.orderMapper.fromOrderEntity(orderEntity);
        } catch (Exception e) {
            log.error("Erro ao atualizar status do pedido: " + e.getMessage());
            throw new OrderException(e.getMessage());
        }
    }

}
