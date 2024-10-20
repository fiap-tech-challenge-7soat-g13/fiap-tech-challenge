package com.fiap.challenge.tastefood.app.adapter.output.external.mercadopago;

import com.fiap.challenge.tastefood.app.adapter.output.external.mercadopago.feign.CreateOrderResponse;
import com.fiap.challenge.tastefood.app.adapter.output.external.mercadopago.feign.GetOrderResponse;
import com.fiap.challenge.tastefood.app.adapter.output.external.mercadopago.feign.MercadoPagoFeignClient;
import com.fiap.challenge.tastefood.core.domain.Customer;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.OrderProduct;
import com.fiap.challenge.tastefood.core.domain.Product;
import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MercadoPagoClientTest {

    @MockBean
    private MercadoPagoFeignClient mercadoPagoFeignClient;

    @Autowired
    private MercadoPagoClient mercadoPagoClient;

    @Test
    void shouldReturnQrData() {

        String expectedQrData = UUID.randomUUID().toString();

        Mockito.when(mercadoPagoFeignClient.createOrder(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(new CreateOrderResponse(expectedQrData, null));

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

        Mockito.when(mercadoPagoFeignClient.getOrder(Mockito.any())).thenReturn(new GetOrderResponse(null, null, "paid"));

        PaymentStatus status = mercadoPagoClient.verifyPayment(UUID.randomUUID().toString());

        assertEquals(expectedStatus, status);
    }


    @Test
    void shouldReturnPending() {

        PaymentStatus expectedStatus = PaymentStatus.PENDENTE;

        Mockito.when(mercadoPagoFeignClient.getOrder(Mockito.any())).thenReturn(new GetOrderResponse(null, null, "payment_in_process"));

        PaymentStatus status = mercadoPagoClient.verifyPayment(UUID.randomUUID().toString());

        assertEquals(expectedStatus, status);
    }

    @Test
    void shouldReturnFailed() {

        PaymentStatus expectedStatus = PaymentStatus.FALHADO;

        Mockito.when(mercadoPagoFeignClient.getOrder(Mockito.any())).thenReturn(new GetOrderResponse(null, null, "failed"));

        PaymentStatus status = mercadoPagoClient.verifyPayment(UUID.randomUUID().toString());

        assertEquals(expectedStatus, status);
    }

}