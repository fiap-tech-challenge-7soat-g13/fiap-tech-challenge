package com.fiap.challenge.tastefood.app.adapter.input.web.order.dto;

import com.fiap.challenge.tastefood.app.adapter.input.web.customer.dto.CustomerResponse;
import com.fiap.challenge.tastefood.app.adapter.input.web.payment.dto.PaymentResponse;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
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
    private PaymentResponse payment;
    private LocalDateTime createdAt;
    private List<OrderProductResponse> products;
    private CustomerResponse customer;
    private BigDecimal total;

}
