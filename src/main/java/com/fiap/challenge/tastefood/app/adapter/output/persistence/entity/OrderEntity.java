package com.fiap.challenge.tastefood.app.adapter.output.persistence.entity;

import com.fiap.challenge.tastefood.core.domain.enums.OrderPaymentStatus;
import com.fiap.challenge.tastefood.core.domain.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private OrderPaymentStatus paymentStatus;

    @ManyToOne
    private CustomerEntity customer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    private List<OrderProductEntity> products;

    private BigDecimal total;

}
