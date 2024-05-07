package com.fiap.challenge.tastefood.core.applications.useCases.order;

import com.fiap.challenge.tastefood.adapter.driven.infra.OrderGateway;
import com.fiap.challenge.tastefood.core.applications.dtos.Checkout;
import com.fiap.challenge.tastefood.core.domain.mapper.ClientMapper;
import com.fiap.challenge.tastefood.core.domain.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.BigInteger.ZERO;

@Service
public class CheckoutOrderUseCase {

    private BigDecimal totalOrderCheckout = new BigDecimal(ZERO);

    private final OrderGateway gateway;
    private final ProductMapper productMapper;
    private final ClientMapper clientMapper;

    public CheckoutOrderUseCase(OrderGateway orderGateway,
                                ProductMapper productMapper,
                                ClientMapper clientMapper) {
        this.gateway = orderGateway;
        this.productMapper = productMapper;
        this.clientMapper = clientMapper;
    }

    public Checkout execute(Checkout checkout) {
        calcTotalCostOrder(checkout);
        return Checkout.builder()
                .products(checkout.getProducts())
                .valueTotalOrder(this.totalOrderCheckout)
                .client(checkout.getClient())
                .build();
    }

    private void calcTotalCostOrder(Checkout checkout) {
        checkout.getProducts().forEach(p -> {
            this.totalOrderCheckout = totalOrderCheckout.add(new BigDecimal(String.valueOf(p.getValor())));
        });
    }

}
