package com.fiap.challenge.tastefood.adapter.driver.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private Long customerId;
    private List<OrderProductRequest> products;

}
