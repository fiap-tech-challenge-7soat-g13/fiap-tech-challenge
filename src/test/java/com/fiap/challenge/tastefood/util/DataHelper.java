package com.fiap.challenge.tastefood.util;

import com.fiap.challenge.tastefood.app.adapter.input.web.customer.dto.CustomerResponse;
import com.fiap.challenge.tastefood.app.adapter.input.web.order.dto.OrderProductRequest;
import com.fiap.challenge.tastefood.app.adapter.input.web.order.dto.OrderProductResponse;
import com.fiap.challenge.tastefood.app.adapter.input.web.order.dto.OrderRequest;
import com.fiap.challenge.tastefood.app.adapter.input.web.order.dto.OrderResponse;
import com.fiap.challenge.tastefood.app.adapter.input.web.payment.dto.PaymentResponse;
import com.fiap.challenge.tastefood.app.adapter.input.web.product.dto.ProductRequest;
import com.fiap.challenge.tastefood.app.adapter.input.web.product.dto.ProductResponse;
import com.fiap.challenge.tastefood.app.adapter.output.externalapis.payment.CreatePaymentOrderRequest;
import com.fiap.challenge.tastefood.app.adapter.output.externalapis.payment.CustomerRequest;
import com.fiap.challenge.tastefood.app.adapter.output.persistence.entity.ProductEntity;
import com.fiap.challenge.tastefood.core.domain.*;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.domain.enums.PaymentStatus;
import com.fiap.challenge.tastefood.core.domain.enums.ProductCategory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataHelper {

    public static OrderRequest getOrderRequestMock() {
        OrderRequest orderRequest = new OrderRequest();

        orderRequest.setCustomerId(UUID.fromString("9ad62775-aea0-48a4-9e0e-42c7438fd844"));
        orderRequest.setProducts(List.of(getOrderProductRequestMock()));

        return orderRequest;
    }

    public static OrderProductRequest getOrderProductRequestMock() {
        OrderProductRequest orderProductRequest = new OrderProductRequest();

        orderProductRequest.setProductId(1L);
        orderProductRequest.setQuantity(10);

        return orderProductRequest;
    }

    public static Order getOrderMock(Long id) {
        Order order = new Order();

        if (id != null)
            order.setId(id);

        order.setTotal(BigDecimal.valueOf(100));
        order.setCreatedAt(LocalDateTime.of(2024, 1, 1, 10, 10));
        order.setCustomer(getCustomerMock(UUID.fromString("9ad62775-aea0-48a4-9e0e-42c7438fd844")));
        order.setProducts(List.of(getOrderProductMock(1L)));
        order.setPayment(getPaymentMock(1L));
        order.setStatus(OrderStatus.CRIADO);

        return order;
    }

    public static Customer getCustomerMock(UUID id) {
        Customer customer = new Customer();

        if (id != null)
            customer.setId(id);

        customer.setName("Nome 1");
        customer.setEmail("teste@teste.com.br");
        customer.setDocument("01234567890");

        return customer;
    }

    public static OrderProduct getOrderProductMock(Long id) {
        OrderProduct orderProduct = new OrderProduct();

        if (id != null)
            orderProduct.setId(id);

        orderProduct.setProduct(getProductMock(1L));
        orderProduct.setQuantity(10);
        orderProduct.setPrice(BigDecimal.valueOf(100));

        return orderProduct;
    }

    public static Product getProductMock(Long id) {
        Product product = new Product();

        if (id != null)
            product.setId(id);

        product.setName("Produto Teste");
        product.setName("Produto Teste");
        product.setDescription("Descrição do produto");
        product.setPrice(BigDecimal.valueOf(10));
        product.setCategory(ProductCategory.LANCHE);
        product.setActive(Boolean.TRUE);
        product.setImages(List.of(""));

        return product;
    }

    public static Payment getPaymentMock(Long id) {
        Payment payment = new Payment();

        if (id != null)
            payment.setId(id);

        payment.setStatus(PaymentStatus.APROVADO);
        payment.setQrCode("");

        return payment;
    }

    public static OrderResponse getOrderResponseMock(Long id) {
        OrderResponse orderResponse = new OrderResponse();

        if (id != null)
            orderResponse.setId(id);

        orderResponse.setTotal(BigDecimal.valueOf(100));
        orderResponse.setCreatedAt(LocalDateTime.of(2024, 1, 1, 10, 10));
        orderResponse.setCustomer(getCustomerResponseMock(UUID.fromString("9ad62775-aea0-48a4-9e0e-42c7438fd844")));
        orderResponse.setProducts(List.of(getOrderProductResponseMock(1L)));
        orderResponse.setPayment(getPaymentResponseMock(1L));
        orderResponse.setStatus(OrderStatus.CRIADO);

        return orderResponse;
    }

    public static CustomerResponse getCustomerResponseMock(UUID id) {
        CustomerResponse customer = new CustomerResponse();

        if (id != null)
            customer.setId(id);

        customer.setName("Nome 1");
        customer.setEmail("teste@teste.com.br");
        customer.setDocument("01234567890");

        return customer;
    }

    public static OrderProductResponse getOrderProductResponseMock(Long id) {
        OrderProductResponse orderProduct = new OrderProductResponse();

        orderProduct.setProduct(getProductResponseMock(1L));
        orderProduct.setQuantity(10);
        orderProduct.setPrice(BigDecimal.valueOf(100));

        return orderProduct;
    }

    public static ProductResponse getProductResponseMock(Long id) {
        ProductResponse product = new ProductResponse();

        if (id != null)
            product.setId(id);

        product.setName("Produto Teste");
        product.setDescription("Descrição do produto");
        product.setPrice(BigDecimal.valueOf(10));
        product.setCategory(ProductCategory.LANCHE);
        product.setImages(List.of(""));

        return product;
    }

    public static PaymentResponse getPaymentResponseMock(Long id) {
        PaymentResponse payment = new PaymentResponse();

        if (id != null)
            payment.setId(id);

        payment.setStatus(PaymentStatus.APROVADO);
        payment.setQrCode("");

        return payment;
    }

    public static CreatePaymentOrderRequest getCreatePaymentOrderRequestMock(Long id) {
        CreatePaymentOrderRequest payment = new CreatePaymentOrderRequest();

        if (id != null)
            payment.setId(id);

        payment.setTotal(BigDecimal.valueOf(100));
        payment.setProducts(List.of(new com.fiap.challenge.tastefood.app.adapter.output.externalapis.payment.OrderProductRequest()));
        payment.setCustomer(new CustomerRequest());
        payment.setStatus(OrderStatus.CRIADO);
        payment.setCreatedAt(LocalDateTime.of(2024, 1, 1, 10, 10));

        return payment;
    }

    public static ProductRequest getProductRequestMock() {
        ProductRequest productRequest = new ProductRequest();

        productRequest.setName("Produto Teste");
        productRequest.setDescription("Descrição do produto");
        productRequest.setPrice(BigDecimal.valueOf(10));
        productRequest.setCategory(ProductCategory.LANCHE);
        productRequest.setImages(List.of(""));

        return productRequest;
    }

    public static ProductEntity getProductEntityMock(Long id) {
        ProductEntity product = new ProductEntity();

        if (id != null)
            product.setId(id);

        product.setName("Produto Teste");
        product.setName("Produto Teste");
        product.setDescription("Descrição do produto");
        product.setPrice(BigDecimal.valueOf(10));
        product.setCategory(ProductCategory.LANCHE);
        product.setActive(Boolean.TRUE);
        product.setImages(List.of(""));

        return product;
    }

}
