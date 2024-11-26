package com.fiap.challenge.tastefood.app.adapter.output.externalapis;

import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.Payment;

public interface PaymentClient {

    Payment createPayment(Order order);

    Payment getPayment(Long paymentId);

}
