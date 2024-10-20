package com.fiap.challenge.tastefood.core.usecases.order;

import com.fiap.challenge.tastefood.core.common.validator.OrderCreateValidator;
import com.fiap.challenge.tastefood.core.domain.Order;
import com.fiap.challenge.tastefood.core.domain.OrderProduct;
import com.fiap.challenge.tastefood.core.domain.Product;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import com.fiap.challenge.tastefood.core.gateways.OrderGateway;
import com.fiap.challenge.tastefood.core.gateways.ProductGateway;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class OrderCreateUseCaseTest {

    private final ProductGateway productGateway = mock(ProductGateway.class);

    private final OrderGateway orderGateway = mock(OrderGateway.class);

    private final OrderCreateValidator orderCreateValidator = mock(OrderCreateValidator.class);

    private final OrderCreateUseCase orderCreateUseCase = new OrderCreateUseCase(productGateway, orderGateway, orderCreateValidator);

    @Test
    void shouldSaveOrder() {

        LocalDateTime startTestDateTime = LocalDateTime.now();

        Product product = new Product();

        product.setId(1L);
        product.setPrice(BigDecimal.TEN);

        OrderProduct orderProduct = new OrderProduct();

        orderProduct.setProduct(product);
        orderProduct.setQuantity(2);

        Order order = new Order();

        order.setProducts(List.of(orderProduct));

        when(productGateway.findById(product.getId())).thenReturn(Optional.of(product));

        orderCreateUseCase.execute(order);

        verify(orderGateway).save(order);

        assertTrue(order.getCreatedAt().isAfter(startTestDateTime));
        assertEquals(OrderStatus.CRIADO, order.getStatus());
        assertEquals(BigDecimal.valueOf(orderProduct.getQuantity()).multiply(product.getPrice()), order.getTotal());

        assertEquals(product.getPrice(), orderProduct.getPrice());
    }

}