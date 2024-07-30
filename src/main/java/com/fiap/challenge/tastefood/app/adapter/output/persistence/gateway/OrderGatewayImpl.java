package com.fiap.challenge.tastefood.app.adapter.output.persistence.gateway;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.OrderEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.repository.OrderRepository;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class OrderGatewayImpl implements OrderGateway {

	private final OrderRepository repository;

	public OrderEntity save(OrderEntity orderEntity) {
		return repository.save(orderEntity);
	}

	public Optional<OrderEntity> findById(Long id) {
		return repository.findById(id);
	}

	public List<OrderEntity> findAll() {
		return repository.findAll();
	}

	public List<OrderEntity> findByStatus(OrderStatus status) {
		return repository.findByStatus(status);
	}

	public List<OrderEntity> findAllByStatusInOrderByCreatedAt(List<String> orderStatus) {
		return repository.findAllByStatusInOrderByCreatedAt(orderStatus);
	}

}
