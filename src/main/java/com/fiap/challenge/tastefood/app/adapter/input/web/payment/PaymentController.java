package com.fiap.challenge.tastefood.app.adapter.input.web.payment;

import com.fiap.challenge.tastefood.core.usecases.payment.PaymentVerifyUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
public class PaymentController {

    private final PaymentVerifyUseCase paymentVerifyUseCase;

    @PostMapping(path = "/payment/{uuid}/callback")
    public void verify(@PathVariable UUID uuid, @RequestParam(required = false) String id, @RequestParam String topic) {
        log.info("Callback received for payment {}: externalId={} topic={}", uuid, id, topic);
        if (List.of("merchant_order", "payment").contains(topic)) {
            paymentVerifyUseCase.execute(uuid, id);
        }
    }

}
