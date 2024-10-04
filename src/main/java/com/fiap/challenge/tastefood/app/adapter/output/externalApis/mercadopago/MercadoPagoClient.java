package com.fiap.challenge.tastefood.app.adapter.output.externalApis.mercadopago;

import com.fiap.challenge.tastefood.app.adapter.output.externalApis.PaymentClient;
import com.fiap.challenge.tastefood.app.adapter.output.externalApis.mercadopago.feign.*;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.OrderProduct;
import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class MercadoPagoClient implements PaymentClient {

    private static final String CALLBACK_PATH = "/payment/%s/callback";

    @Value("${mercadopago.api.user-id}")
    private String userId;

    @Value("${mercadopago.api.pos-id}")
    private String posId;

    @Value("${mercadopago.api.callback-url}")
    private String callbackUrl;

    private final MercadoPagoFeignClient client;

    @Override
    public String createPayment(UUID paymentUuid, Order order) {
        try {
            CreateOrderRequest request = toCreateOrderRequest(paymentUuid, order);
            CreateOrderResponse response = client.createOrder(userId, posId, request);
            return response.getQrData();
        } catch (Exception e) {
            log.error("An error has occurred when creating the payment", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public PaymentStatus verifyPayment(String paymentId) {
        try {
            GetOrderResponse res = this.client.getOrder(paymentId);
            return switch (res.getOrderStatus()) {
                case "paid" -> PaymentStatus.APROVADO;
                case "payment_required", "payment_in_process" -> PaymentStatus.PENDENTE;
                default -> PaymentStatus.FALHADO;
            };
        } catch (Exception e) {
            log.error("An error has occurred when verifying the payment", e);
            throw new RuntimeException(e);
        }
    }

    private CreateOrderRequest toCreateOrderRequest(UUID paymentUuid, Order order) {
        return CreateOrderRequest.builder()
                .title(String.format("Order #%s", order.getId()))
                .description(String.format("Order created at %s by customer %s", order.getCreatedAt(), order.getCustomer().getDocument()))
                .totalAmount(order.getTotal())
                .externalReference(order.getId().toString())
                .notificationUrl(callbackUrl + String.format(CALLBACK_PATH, paymentUuid))
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
