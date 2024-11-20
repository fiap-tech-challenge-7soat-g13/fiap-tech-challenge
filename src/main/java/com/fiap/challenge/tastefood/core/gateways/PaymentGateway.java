package com.fiap.challenge.tastefood.core.gateways;

import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.Payment;
import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;

import java.util.Optional;
import java.util.UUID;

public interface PaymentGateway {

    Payment save(Payment payment);

    Optional<Payment> findByUuid(UUID uuid);

    Optional<Payment> findByOrderId(Long orderId);

    String generatePayment(UUID paymentUuid, Order order);

    PaymentStatus verifyPayment(Payment payment);

}
