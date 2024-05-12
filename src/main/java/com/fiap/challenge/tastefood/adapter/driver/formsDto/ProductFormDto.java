package com.fiap.challenge.tastefood.adapter.driver.formsDto;

import com.fiap.challenge.tastefood.core.domain.entities.CategoryEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductFormDto {

	private String description;
	private String brand;
	private CategoryEnum category;
	private BigDecimal value;

}
