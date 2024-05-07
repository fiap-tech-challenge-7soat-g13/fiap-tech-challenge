package com.fiap.challenge.tastefood.core.applications.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Value
@Builder(toBuilder = true)
public class Order {

    private Long id;
    private StatusOrderEnum statusOrderEnum;
    private LocalDateTime dateOrder;
    private List<Product> products;
    private Client client;

}
