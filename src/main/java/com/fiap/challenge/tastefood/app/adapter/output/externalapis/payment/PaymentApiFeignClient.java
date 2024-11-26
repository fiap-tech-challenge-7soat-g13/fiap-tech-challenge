package com.fiap.challenge.tastefood.app.adapter.output.externalapis.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "payment")
interface PaymentApiFeignClient {

    @PostMapping
    GetPaymentResponse create(CreatePaymentOrderRequest orderRequest);

    @GetMapping("/{paymentId}")
    GetPaymentResponse getPayment(@PathVariable Long paymentId);

}
