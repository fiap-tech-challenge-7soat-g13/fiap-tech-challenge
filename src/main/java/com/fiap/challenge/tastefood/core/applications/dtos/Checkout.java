package com.fiap.challenge.tastefood.core.applications.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class Checkout {

    private List<Product> products;
    private Client client;
    private BigDecimal valueTotalOrder;

}
