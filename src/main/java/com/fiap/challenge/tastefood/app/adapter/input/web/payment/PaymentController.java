package com.fiap.challenge.tastefood.app.adapter.input.web.payment;

import com.fiap.challenge.tastefood.core.useCases.payment.PaymentVerifyUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
public class PaymentController {

    private final PaymentVerifyUseCase paymentVerifyUseCase;

    @PostMapping(path = "/payment/{uuid}/callback")
    public ResponseEntity<Void> verify(@PathVariable UUID uuid, @RequestParam(required = false) String id, @RequestParam String topic) {
        log.info("Callback received for payment {}: externalId={} topic={}", uuid, id, topic);
        switch (topic) {
            case "merchant_order", "payment":
                paymentVerifyUseCase.execute(uuid, id);
        }
        return ResponseEntity.ok().build();
    }

}
