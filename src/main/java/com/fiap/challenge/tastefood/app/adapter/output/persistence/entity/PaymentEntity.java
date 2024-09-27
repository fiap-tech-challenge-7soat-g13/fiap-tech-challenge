package com.fiap.challenge.tastefood.app.adapter.output.persistence.entity;

import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class PaymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private OrderEntity order;

	@Column(name = "payment_at")
	private LocalDateTime paymentAt;

	private BigDecimal total;

	@Column(name = "qr_data")
	private String qrData;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private PaymentStatus paymentStatus;

}
