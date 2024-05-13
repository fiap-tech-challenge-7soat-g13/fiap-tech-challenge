package com.fiap.challenge.tastefood.core.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private String brand;
    private ProductCategoryEnum category;
    private BigDecimal price;

}
