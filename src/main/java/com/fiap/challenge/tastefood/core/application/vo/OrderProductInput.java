package com.fiap.challenge.tastefood.core.application.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductInput {

    private Integer quantity;
    private Long productId;

}
