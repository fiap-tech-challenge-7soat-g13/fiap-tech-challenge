package com.fiap.challenge.tastefood.app.adapter.input.web.order.dto;

import com.fiap.challenge.tastefood.core.domain.enums.ProductCategory;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductRequest {

	private String name;
	private String description;
	private ProductCategory category;
	private BigDecimal price;
	private List<String> images;

}
