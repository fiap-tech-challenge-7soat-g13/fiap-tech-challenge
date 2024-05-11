package com.fiap.challenge.tastefood.core.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String brand;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    private BigDecimal value;

}
