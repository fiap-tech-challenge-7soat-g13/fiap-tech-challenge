package com.fiap.challenge.tastefood.app.adapter.output.externalApis;

import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;

import java.util.UUID;

public interface PaymentClient {

    String createPayment(UUID paymentUuid, Order order);

    PaymentStatus verifyPayment(String paymentId);

}
