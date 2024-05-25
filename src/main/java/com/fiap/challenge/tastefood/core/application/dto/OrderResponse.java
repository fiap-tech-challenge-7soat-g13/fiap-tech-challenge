package com.fiap.challenge.tastefood.core.application.dto;

import com.fiap.challenge.tastefood.core.domain.valueObject.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponse {

    private Long id;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private List<OrderProductResponse> products;
    private CustomerResponse customer;
    private BigDecimal total;

}
