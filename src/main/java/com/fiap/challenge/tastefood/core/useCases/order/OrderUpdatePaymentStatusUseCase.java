package com.fiap.challenge.tastefood.core.useCases.order;

import org.springframework.stereotype.Service;

import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.enums.OrderPaymentStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderUpdatePaymentStatusUseCase {

	private final OrderGateway orderGateway;

	public Order execute(Long id, OrderPaymentStatus paymentStatus) {
		Order order = orderGateway.findById(id).orElseThrow();
		order.setPaymentStatus(paymentStatus);
		order.setStatus(paymentStatus.getOrderStatus());
		return orderGateway.save(order);
	}

}
