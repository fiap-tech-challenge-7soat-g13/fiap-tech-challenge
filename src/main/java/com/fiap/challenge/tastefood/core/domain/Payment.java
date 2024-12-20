package com.fiap.challenge.tastefood.core.domain;

import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Payment {

    private Long id;
    private String qrCode;
    private PaymentStatus status;

}
