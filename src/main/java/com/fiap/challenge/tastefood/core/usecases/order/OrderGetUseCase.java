package com.fiap.challenge.tastefood.core.usecases.order;

import org.springframework.stereotype.Service;

import com.fiap.challenge.tastefood.core.common.exception.EntityNotFoundException;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderGetUseCase {

	private final OrderGateway orderGateway;

	public Order execute(Long id) {
		return orderGateway.findById(id).orElseThrow(EntityNotFoundException::new);
	}

}
