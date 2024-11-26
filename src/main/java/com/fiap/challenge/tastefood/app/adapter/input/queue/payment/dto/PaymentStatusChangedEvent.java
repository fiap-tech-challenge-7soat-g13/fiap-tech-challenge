package com.fiap.challenge.tastefood.app.adapter.input.queue.payment.dto;

import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;
import lombok.Getter;

@Getter
public class PaymentStatusChangedEvent {

    private Long id;
    private String qrCode;
    private PaymentStatus status;

}
