package com.fiap.challenge.tastefood.core.application.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderProductOutput {

    private Integer quantity;
    private BigDecimal price;
    private ProductOutput product;

}
