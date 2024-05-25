package com.fiap.challenge.tastefood.core.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductRequest {

    private Integer quantity;
    private Long productId;

}
