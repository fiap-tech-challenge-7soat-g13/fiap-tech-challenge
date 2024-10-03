package com.fiap.challenge.tastefood.app.adapter.output.persistence.gateway;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fiap.challenge.tastefood.app.adapter.output.externalApis.PaymentClient;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.PaymentEntity;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.mapper.PaymentMapper;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.repository.PaymentRepository;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.Payment;
import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;
import com.fiap.challenge.tastefood.core.gateways.PaymentGateway;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PaymentGatewayImpl implements PaymentGateway {

	private final PaymentRepository paymentRepository;
	private final PaymentClient paymentClient;
	private final PaymentMapper paymentMapper;

	@Transactional
	public Payment save(Payment payment) {
		PaymentEntity paymentEntity = paymentMapper.toPaymentEntity(payment);
		PaymentEntity productSave = paymentRepository.save(paymentEntity);
		return paymentMapper.toPayment(productSave);
	}

	@Override
	public Optional<Payment> findByUuid(UUID uuid) {
		return paymentRepository.findByUuid(uuid).map(paymentMapper::toPayment);
	}

	@Override
	public String generatePayment(UUID paymentUuid, Order order) {
		return paymentClient.createPayment(paymentUuid, order);
	}

	@Override
	public PaymentStatus verifyPayment(Payment payment) {
		return paymentClient.verifyPayment(payment.getExternalId());
	}

}
