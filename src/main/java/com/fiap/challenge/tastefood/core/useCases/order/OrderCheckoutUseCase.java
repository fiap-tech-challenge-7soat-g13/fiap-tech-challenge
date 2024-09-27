package com.fiap.challenge.tastefood.core.useCases.order;

import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.Payment;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import com.fiap.challenge.tastefood.core.gateways.PaymentGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderCheckoutUseCase {

    private final OrderGateway orderGateway;
    private final PaymentGateway paymentGateway;

    @Transactional
    public Order execute(Long id) {
        Order order = orderGateway.findById(id).orElseThrow();
        Payment payment = paymentGateway.generateQRCode(id);
        if (payment != null) {
            order.setStatus(OrderStatus.RECEBIDO);
            order.setPayment(payment);
            return orderGateway.save(order);
        }

        return order;
    }

}
