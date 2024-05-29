package com.fiap.challenge.tastefood.core.application.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderInput {

    private Long customerId;
    private List<OrderProductInput> products;

}
