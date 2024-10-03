package com.fiap.challenge.tastefood.app.adapter.input.web.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductRequest {

    private Integer quantity;
    private Long productId;

}
