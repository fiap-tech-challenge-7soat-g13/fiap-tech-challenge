package com.fiap.challenge.tastefood.app.adapter.output.persistence.entity;

import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    private String qrCode;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String externalId;

}
