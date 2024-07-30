package com.fiap.challenge.tastefood.core.gateways;

import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderGateway {

	Order save(Order order);

	Optional<Order> findById(Long id);

	List<Order> findAll();

	List<Order> findByStatus(OrderStatus status);

	List<Order> findAllByStatusInOrderByCreatedAt(List<String> orderStatus);

}
