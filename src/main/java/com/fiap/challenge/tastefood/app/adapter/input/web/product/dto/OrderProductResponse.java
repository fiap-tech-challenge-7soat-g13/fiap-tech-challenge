package com.fiap.challenge.tastefood.app.adapter.input.web.product.dto;

import com.fiap.challenge.tastefood.app.adapter.input.web.order.dto.ProductResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderProductResponse {

    private Integer quantity;
    private BigDecimal price;
    private ProductResponse product;

}
