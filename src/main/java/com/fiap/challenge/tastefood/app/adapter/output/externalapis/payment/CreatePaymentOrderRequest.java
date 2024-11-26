package com.fiap.challenge.tastefood.app.adapter.output.externalapis.payment;

import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CreatePaymentOrderRequest {

    private Long id;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private List<OrderProductRequest> products;
    private CustomerRequest customer;
    private BigDecimal total;

}
