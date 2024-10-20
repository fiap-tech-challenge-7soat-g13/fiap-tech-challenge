package com.fiap.challenge.tastefood.app.adapter.output.externalapis.mercadopago;

import com.fiap.challenge.tastefood.app.adapter.output.externalapis.mercadopago.feign.CreateOrderResponse;
import com.fiap.challenge.tastefood.app.adapter.output.externalapis.mercadopago.feign.GetOrderResponse;
import com.fiap.challenge.tastefood.app.adapter.output.externalapis.mercadopago.feign.MercadoPagoFeignClient;
import com.fiap.challenge.tastefood.core.domain.Customer;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.OrderProduct;
import com.fiap.challenge.tastefood.core.domain.Product;
import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MercadoPagoClientTest {

    private final MercadoPagoFeignClient mercadoPagoFeignClient = mock(MercadoPagoFeignClient.class);

    private final MercadoPagoClient mercadoPagoClient = new MercadoPagoClient(mercadoPagoFeignClient);

    @Test
    void shouldReturnQrData() {

        String expectedQrData = UUID.randomUUID().toString();

        when(mercadoPagoFeignClient.createOrder(any(), any(), any())).thenReturn(new CreateOrderResponse(expectedQrData, null));

        Customer customer = new Customer();
        Product product = new Product();

        OrderProduct orderProduct = new OrderProduct();

        orderProduct.setProduct(product);
        orderProduct.setQuantity(1);
        orderProduct.setPrice(BigDecimal.TEN);

        Order order = new Order();

        order.setId(1L);
        order.setCustomer(customer);
        order.setProducts(List.of(orderProduct));

        String qrData = mercadoPagoClient.createPayment(UUID.randomUUID(), order);

        assertEquals(expectedQrData, qrData);
    }

    @Test
    void shouldReturnApproved() {

        PaymentStatus expectedStatus = PaymentStatus.APROVADO;

        when(mercadoPagoFeignClient.getOrder(any())).thenReturn(new GetOrderResponse(null, null, "paid"));

        PaymentStatus status = mercadoPagoClient.verifyPayment(UUID.randomUUID().toString());

        assertEquals(expectedStatus, status);
    }


    @Test
    void shouldReturnPending() {

        PaymentStatus expectedStatus = PaymentStatus.PENDENTE;

        when(mercadoPagoFeignClient.getOrder(any())).thenReturn(new GetOrderResponse(null, null, "payment_in_process"));

        PaymentStatus status = mercadoPagoClient.verifyPayment(UUID.randomUUID().toString());

        assertEquals(expectedStatus, status);
    }

    @Test
    void shouldReturnFailed() {

        PaymentStatus expectedStatus = PaymentStatus.FALHADO;

        when(mercadoPagoFeignClient.getOrder(any())).thenReturn(new GetOrderResponse(null, null, "failed"));

        PaymentStatus status = mercadoPagoClient.verifyPayment(UUID.randomUUID().toString());

        assertEquals(expectedStatus, status);
    }

}