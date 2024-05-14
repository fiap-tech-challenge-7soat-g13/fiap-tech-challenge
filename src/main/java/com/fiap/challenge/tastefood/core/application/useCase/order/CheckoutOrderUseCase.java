package com.fiap.challenge.tastefood.core.application.useCase.order;

import com.fiap.challenge.tastefood.adapter.driver.infra.OrderGateway;
import com.fiap.challenge.tastefood.core.application.dto.Checkout;
import com.fiap.challenge.tastefood.core.application.dto.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class CheckoutOrderUseCase {

    private final OrderGateway gateway;

    public Checkout execute(Checkout checkout) {
        return Checkout.builder()
                .products(checkout.getProducts())
                .valueTotalOrder(calcTotalCostOrder(checkout))
                .customer(checkout.getCustomer())
                .build();
    }

    private BigDecimal calcTotalCostOrder(Checkout checkout) {
        return checkout.getProducts().stream().map(ProductResponse::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
