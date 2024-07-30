package com.fiap.challenge.tastefood.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderPaymentStatus {

	PENDENTE(OrderStatus.CRIADO),
	APROVADO(OrderStatus.RECEBIDO),
	RECUSADO(OrderStatus.CANCELADO);

	private final OrderStatus orderStatus;

}
