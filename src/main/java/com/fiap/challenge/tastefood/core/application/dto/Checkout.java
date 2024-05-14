package com.fiap.challenge.tastefood.core.application.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class Checkout {

    private List<ProductResponse> products;
    private CustomerResponse customer;
    private BigDecimal valueTotalOrder;

}
