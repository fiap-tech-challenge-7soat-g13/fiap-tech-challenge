package com.fiap.challenge.tastefood.core.domain;

import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class Payment {

    private Long id;
    private UUID uuid;
    private Order order;
    private String qrCode;
    private PaymentStatus status;
    private String externalId;

}
