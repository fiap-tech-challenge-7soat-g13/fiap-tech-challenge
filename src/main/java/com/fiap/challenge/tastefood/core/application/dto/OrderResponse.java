package com.fiap.challenge.tastefood.core.application.dto;

import com.fiap.challenge.tastefood.core.domain.entity.OrderStatusEnum;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private OrderStatusEnum status;
    private LocalDateTime createdAt;
    private List<OrderProductResponse> products;
    private CustomerResponse customer;

}
