package com.fiap.challenge.tastefood.core.useCases.payment;

import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.Payment;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import com.fiap.challenge.tastefood.core.gateways.PaymentGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentVerifyUseCase {

    private final OrderGateway orderGateway;
    private final PaymentGateway paymentGateway;

    public void execute(UUID uuid, String id) {

        Payment payment = paymentGateway.findByUuid(uuid).orElseThrow();

        if (!payment.getStatus().equals(PaymentStatus.PENDENTE)) {
            return;
        }

        if (payment.getExternalId() == null) {
            payment.setExternalId(id);
            paymentGateway.save(payment);
        }

        PaymentStatus newStatus = paymentGateway.verifyPayment(payment);

        if (newStatus.equals(PaymentStatus.PENDENTE)) {
            return;
        }

        payment.setStatus(newStatus);

        paymentGateway.save(payment);

        Order order = payment.getOrder();

        order.setStatus(toOrderStatus(payment.getStatus()));

        orderGateway.save(order);
    }

    private OrderStatus toOrderStatus(PaymentStatus status) {
        return switch (status) {
            case APROVADO -> OrderStatus.EM_PREPARACAO;
            case FALHADO -> OrderStatus.CANCELADO;
            default -> throw new IllegalArgumentException();
        };
    }

}
