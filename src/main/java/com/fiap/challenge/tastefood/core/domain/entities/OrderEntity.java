package com.fiap.challenge.tastefood.core.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    private LocalDateTime dateOrder;

    private StatusOrderEnum statusOrderEnum;

    @ManyToMany(cascade=CascadeType.ALL)
    private List<ProductEntity> products;

    @ManyToOne(cascade=CascadeType.PERSIST)
    private ClientEntity client;

}
