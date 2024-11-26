package com.fiap.challenge.tastefood.core.usecases.payment;

import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.Payment;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentVerifyUseCase {

    private final OrderGateway orderGateway;

    public void execute(Payment payment) {

        Order order = orderGateway.findByPaymentId(payment.getId());

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
