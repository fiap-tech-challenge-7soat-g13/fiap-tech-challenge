package com.fiap.challenge.tastefood.core.gateways;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.OrderEntity;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderGateway {

	OrderEntity save(OrderEntity orderEntity);

	Optional<OrderEntity> findById(Long id);

	List<OrderEntity> findAll();

	List<OrderEntity> findByStatus(OrderStatus status);

	List<OrderEntity> findAllByStatusInOrderByCreatedAt(List<String> orderStatus);

}
