package com.fiap.challenge.tastefood.app.adapter.output.externalapis.payment;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderProductRequest {

    private Long id;
    private Integer quantity;
    private BigDecimal price;
    private ProductRequest product;

}
