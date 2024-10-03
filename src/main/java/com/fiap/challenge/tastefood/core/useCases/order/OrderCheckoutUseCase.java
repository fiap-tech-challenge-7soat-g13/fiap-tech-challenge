package com.fiap.challenge.tastefood.core.useCases.order;

import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.Payment;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import com.fiap.challenge.tastefood.core.gateways.PaymentGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderCheckoutUseCase {

    private final OrderGateway orderGateway;
    private final PaymentGateway paymentGateway;

    @Transactional
    public Order execute(Long id) {

        Order order = orderGateway.findById(id).orElseThrow();

        UUID paymentUuid = UUID.randomUUID();

        String qrCode = paymentGateway.generatePayment(paymentUuid, order);

        Payment payment = createPayment(paymentUuid, qrCode, order);

        order.setStatus(OrderStatus.RECEBIDO);
        order.setPayment(payment);

        return orderGateway.save(order);
    }

    private Payment createPayment(UUID paymentUuid, String qrCode, Order order) {

        Payment payment = new Payment();

        payment.setUuid(paymentUuid);
        payment.setQrCode(qrCode);
        payment.setStatus(PaymentStatus.PENDENTE);
        payment.setOrder(order);

        return paymentGateway.save(payment);
    }

}
