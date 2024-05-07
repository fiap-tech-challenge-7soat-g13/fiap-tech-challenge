package com.fiap.challenge.tastefood.core.applications.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Value
@Builder(toBuilder = true)
public class Checkout {

    private List<Product> products;
    private Client client;
    private BigDecimal valueTotalOrder;

}
