package com.fiap.challenge.tastefood.adapter.driven.infra;

import com.fiap.challenge.tastefood.core.domain.entities.OrderEntity;
import com.fiap.challenge.tastefood.core.domain.entities.OrderStatusEnum;

import java.util.List;

public interface OrderGateway {

	List<OrderEntity> findAll();

	Long create(OrderEntity orderEntity);

	List<OrderEntity> findByStatus(OrderStatusEnum status);

	OrderEntity findbyId(Long idOrder);

	OrderEntity updateStatusOrder(Long id, OrderStatusEnum orderStatusEnum);

	OrderEntity update(OrderEntity orderEntity);

}
