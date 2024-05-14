package com.fiap.challenge.tastefood.core.application.dto;

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
    private OrderStatusEnum status;
    private LocalDateTime date;
    private List<ProductResponse> products;
    private CustomerResponse customer;

}
