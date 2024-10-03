package com.fiap.challenge.tastefood.core.gateways;

import java.util.Optional;
import java.util.UUID;

import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.Payment;
import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;

public interface PaymentGateway {

	Payment save(Payment payment);

	Optional<Payment> findByUuid(UUID uuid);

	String generatePayment(UUID paymentUuid, Order order);

	PaymentStatus verifyPayment(Payment payment);

}
