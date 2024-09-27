package com.fiap.challenge.tastefood.app.adapter.output.externalApis.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDTO {

	private String category;
	private String title;
	private String description;
	private BigDecimal unitPrice;
	private Long quantity;
	private String unitMeasure;
	private BigDecimal totalAmount;

}
