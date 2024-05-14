package com.fiap.challenge.tastefood.core.application.useCase.order;

import com.fiap.challenge.tastefood.adapter.driver.infra.OrderGateway;
import com.fiap.challenge.tastefood.core.application.dto.Order;
import com.fiap.challenge.tastefood.core.application.dto.OrderStatusEnum;
import com.fiap.challenge.tastefood.core.domain.entity.OrderEntity;
import com.fiap.challenge.tastefood.core.domain.exception.EntityNotFoundException;
import com.fiap.challenge.tastefood.core.domain.exception.InvalidDataException;
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

    public Order execute(Order order) throws InvalidDataException {
        try {
            OrderStatusEnum orderStatusEnum = order.getStatus();
            OrderEntity orderEntity = gateway.findbyId(order.getId()).orElseThrow(EntityNotFoundException::new);

            OrderStatusEnum.isValidUpdateStatus(OrderStatusEnum.getById(orderEntity.getStatus().getName()), orderStatusEnum);

            return this.orderMapper.fromOrderEntity(orderEntity);
        } catch (Exception e) {
            log.error("Erro ao atualizar status do pedido: " + e.getMessage());
            throw new InvalidDataException(e.getMessage());
        }
    }

}
