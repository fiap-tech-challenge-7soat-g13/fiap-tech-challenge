package com.fiap.challenge.tastefood.app.adapter.output.externalapis.payment;

import com.fiap.challenge.tastefood.app.adapter.output.externalapis.PaymentClient;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.Payment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentApiClient implements PaymentClient {

    private final PaymentApiFeignClient paymentApiFeignClient;
    private final GetPaymentResponseMapper getPaymentResponseMapper;
    private final CreatePaymentOrderRequestMapper orderRequestMapper;

    @Override
    public Payment createPayment(Order order) {
        CreatePaymentOrderRequest orderRequest = orderRequestMapper.toCreatePaymentOrderRequest(order);
        GetPaymentResponse getPaymentResponse = paymentApiFeignClient.create(orderRequest);
        return getPaymentResponseMapper.toPayment(getPaymentResponse);
    }

    @Override
    public Payment getPayment(Long id) {
        GetPaymentResponse getPaymentResponse = paymentApiFeignClient.getPayment(id);
        return getPaymentResponseMapper.toPayment(getPaymentResponse);
    }

}
