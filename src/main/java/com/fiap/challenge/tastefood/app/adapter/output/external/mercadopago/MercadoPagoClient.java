package com.fiap.challenge.tastefood.app.adapter.output.external.mercadopago;

import com.fiap.challenge.tastefood.app.adapter.output.external.PaymentClient;
import com.fiap.challenge.tastefood.app.adapter.output.external.mercadopago.feign.*;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.OrderProduct;
import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MercadoPagoClient implements PaymentClient {

    @Value("${mercadopago.api.userId}")
    private String userId;

    @Value("${mercadopago.api.posId}")
    private String posId;

    @Value("${mercadopago.api.callbackUrl}")
    private String callbackUrl;

    private final MercadoPagoFeignClient client;

    @Override
    public String createPayment(UUID paymentUuid, Order order) {
        CreateOrderRequest request = toCreateOrderRequest(paymentUuid, order);
        CreateOrderResponse response = client.createOrder(userId, posId, request);
        return response.getQrData();
    }

    @Override
    public PaymentStatus verifyPayment(String paymentId) {
        GetOrderResponse res = this.client.getOrder(paymentId);
        return switch (res.getOrderStatus()) {
            case "paid" -> PaymentStatus.APROVADO;
            case "payment_required", "payment_in_process" -> PaymentStatus.PENDENTE;
            default -> PaymentStatus.FALHADO;
        };
    }

    private CreateOrderRequest toCreateOrderRequest(UUID paymentUuid, Order order) {
        return CreateOrderRequest.builder()
                .title(String.format("Order #%s", order.getId()))
                .description(String.format("Order created at %s by customer %s", order.getCreatedAt(), order.getCustomer().getDocument()))
                .totalAmount(order.getTotal())
                .externalReference(order.getId().toString())
                .notificationUrl(callbackUrl + String.format("/payment/%s/callback", paymentUuid))
                .items(order.getProducts().stream().map(this::toCreateOrderItemRequest).toList())
                .build();
    }

    private CreateOrderItemRequest toCreateOrderItemRequest(OrderProduct orderProduct) {
        return CreateOrderItemRequest.builder()
                .title(orderProduct.getProduct().getName())
                .quantity((long) orderProduct.getQuantity())
                .unitPrice(orderProduct.getPrice())
                .totalAmount(orderProduct.getPrice().multiply(BigDecimal.valueOf(orderProduct.getQuantity())))
                .unitMeasure("unit")
                .build();
    }

}
