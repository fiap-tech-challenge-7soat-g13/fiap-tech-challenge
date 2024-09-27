package com.fiap.challenge.tastefood.core.domain;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.OrderEntity;
import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@RequiredArgsConstructor
public class Payment {

	private Long id;
	private OrderEntity orderEntity;
	private LocalDateTime paymentAt;
	private BigDecimal total;
	private String qrData;
	private PaymentStatus paymentStatus;

}
