package com.fiap.challenge.tastefood.app.adapter.input.web.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderRequest {

    private UUID customerId;
    private List<OrderProductRequest> products;

}
