package com.fiap.challenge.tastefood.core.domain.entity;

import com.fiap.challenge.tastefood.core.domain.valueObject.ProductCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    private BigDecimal price;

    private Boolean active;

    private List<String> images;

}
