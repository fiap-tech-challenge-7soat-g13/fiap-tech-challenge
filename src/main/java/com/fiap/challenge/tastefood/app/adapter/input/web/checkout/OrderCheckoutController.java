package com.fiap.challenge.tastefood.app.adapter.input.web.checkout;

import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.PaymentQrCode;
import com.fiap.challenge.tastefood.core.useCases.order.OrderCheckoutUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderCheckoutController {

    private final OrderCheckoutUseCase orderCheckoutUseCase;

    @PostMapping("/order/{id}/qr")
    public ResponseEntity<PaymentQrCode> generateQRCode(@PathVariable Long id) {
        Order order = orderCheckoutUseCase.execute(id);
        return ResponseEntity.ok().body(PaymentQrCode.builder().qrData(order.getPayment().getQrData()).build());
    }

}
