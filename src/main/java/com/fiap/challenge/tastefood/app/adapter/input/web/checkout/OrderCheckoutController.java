package com.fiap.challenge.tastefood.app.adapter.input.web.checkout;

import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.PaymentQrCode;
import com.fiap.challenge.tastefood.core.useCases.order.OrderCheckoutUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderCheckoutController {

    private final OrderCheckoutUseCase orderCheckoutUseCase;

    @Operation(description = "Endpoint para gerar um código QR (futuramente)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Código QR criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Informações de pagamento inválidas."),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado."),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro no servidor.")
    })
    @PostMapping("/order/{id}/qr")
    public ResponseEntity<PaymentQrCode> generateQRCode(@PathVariable Long id) {
        Order order = orderCheckoutUseCase.execute(id);
        return ResponseEntity.ok().body(PaymentQrCode.builder().qrData(order.getPayment().getQrData()).build());
    }

}
