package com.fiap.challenge.tastefood.core.application.vo;

import com.fiap.challenge.tastefood.core.domain.valueObject.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderOutput {

    private Long id;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private List<OrderProductOutput> products;
    private CustomerOutput customer;
    private BigDecimal total;

}
