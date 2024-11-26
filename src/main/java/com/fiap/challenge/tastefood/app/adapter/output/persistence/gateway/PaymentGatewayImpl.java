package com.fiap.challenge.tastefood.app.adapter.output.persistence.gateway;

import com.fiap.challenge.tastefood.app.adapter.output.externalapis.PaymentClient;
import com.fiap.challenge.tastefood.core.domain.Payment;
import com.fiap.challenge.tastefood.core.gateways.PaymentGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PaymentGatewayImpl implements PaymentGateway {

    private final PaymentClient paymentClient;

    @Override
    public Payment findById(Long id) {
        return paymentClient.getPayment(id);
    }

}
