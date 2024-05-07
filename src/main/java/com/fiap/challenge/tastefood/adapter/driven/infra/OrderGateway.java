package com.fiap.challenge.tastefood.adapter.driven.infra;

import com.fiap.challenge.tastefood.core.domain.entities.OrderEntity;
import com.fiap.challenge.tastefood.core.domain.entities.StatusOrderEnum;

import java.util.List;

public interface OrderGateway {

	List<OrderEntity> findAll();

	Long create(OrderEntity orderEntity);

	List<OrderEntity> findByStatus(StatusOrderEnum status);

	OrderEntity findbyId(Long idOrder);

	OrderEntity updateStatusOrder(Long id, StatusOrderEnum statusOrderEnum);

	OrderEntity update(OrderEntity orderEntity);

}
