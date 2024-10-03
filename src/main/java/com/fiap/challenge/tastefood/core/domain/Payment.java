package com.fiap.challenge.tastefood.core.domain;

import java.util.UUID;

import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class Payment {

	private Long id;
	private UUID uuid;
	private Order order;
	private String qrCode;
	private PaymentStatus status;
	private String externalId;

}
