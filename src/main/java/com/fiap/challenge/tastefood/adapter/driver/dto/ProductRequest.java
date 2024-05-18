package com.fiap.challenge.tastefood.adapter.driver.dto;

import com.fiap.challenge.tastefood.core.domain.entity.ProductCategoryEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequest {

	private String name;
	private String description;
	private ProductCategoryEnum category;
	private BigDecimal price;

}
