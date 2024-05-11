package com.fiap.challenge.tastefood.core.applications.useCases.order;

import com.fiap.challenge.tastefood.adapter.driven.infra.OrderGateway;
import com.fiap.challenge.tastefood.core.applications.dtos.Checkout;
import com.fiap.challenge.tastefood.core.applications.dtos.Product;
import com.fiap.challenge.tastefood.core.domain.mapper.ClientMapper;
import com.fiap.challenge.tastefood.core.domain.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class CheckoutOrderUseCase {

    private final OrderGateway gateway;
    private final ProductMapper productMapper;
    private final ClientMapper clientMapper;

    public Checkout execute(Checkout checkout) {
        return Checkout.builder()
                .products(checkout.getProducts())
                .valueTotalOrder(calcTotalCostOrder(checkout))
                .client(checkout.getClient())
                .build();
    }

    private BigDecimal calcTotalCostOrder(Checkout checkout) {
        return checkout.getProducts().stream().map(Product::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
