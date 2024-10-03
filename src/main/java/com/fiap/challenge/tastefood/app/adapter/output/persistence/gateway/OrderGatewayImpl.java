package com.fiap.challenge.tastefood.app.adapter.output.persistence.gateway;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.OrderEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper.OrderMapper;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.repository.OrderRepository;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class OrderGatewayImpl implements OrderGateway {

	private final OrderMapper orderMapper;
	private final OrderRepository repository;

	@Transactional
	public Order save(Order order) {
		OrderEntity orderEntity = orderMapper.toOrderEntity(order);
		OrderEntity orderSave = repository.save(orderEntity);
		return orderMapper.toOrder(orderSave);
	}

	@Transactional
	public Optional<Order> findById(Long id) {
		Optional<OrderEntity> orderEntity = repository.findById(id);
		return orderEntity.map(orderMapper::toOrder);
	}

	@Transactional
	public List<Order> findAll() {
		List<OrderEntity> orderList = repository.findAll();
		return orderMapper.toOrder(orderList);
	}

	@Transactional
	public List<Order> findByStatus(OrderStatus status) {
		List<OrderEntity> orderList = repository.findByStatus(status);
		return orderMapper.toOrder(orderList);
	}

	@Transactional
	public List<Order> findAllByStatusInOrderByStatusDesc(List<String> orderStatus) {
		List<OrderEntity> orderList = repository.findAllByStatusInOrderByStatusDesc(orderStatus);
		return orderMapper.toOrder(orderList);
	}

}
