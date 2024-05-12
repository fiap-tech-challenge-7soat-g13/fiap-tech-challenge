package com.fiap.challenge.tastefood.core.applications.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Product {

    private String description;
    private String brand;
    private CategoryEnum category;
    private BigDecimal value;

}
