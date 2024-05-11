package com.fiap.challenge.tastefood.core.applications.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class Order {

    private Long id;
    private StatusOrderEnum status;
    private LocalDateTime date;
    private List<Product> products;
    private Client client;

}
