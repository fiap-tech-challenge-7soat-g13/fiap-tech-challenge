package com.fiap.challenge.tastefood.core.application.dto;

import com.fiap.challenge.tastefood.core.domain.entity.ProductCategoryEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private ProductCategoryEnum category;
    private BigDecimal price;
    private List<String> images;

}
