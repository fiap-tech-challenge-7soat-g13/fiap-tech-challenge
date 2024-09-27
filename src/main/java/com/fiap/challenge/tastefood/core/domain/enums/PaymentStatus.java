package com.fiap.challenge.tastefood.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentStatus {

	PENDENTE("Pendente"),
	APROVADO("Aprovado"),
	RECUSADO("Recusado");

	final String descricao;

}
