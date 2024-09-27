package com.fiap.challenge.tastefood.app.adapter.output.externalApis.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MercadoPagoRequestDTO {

	private String externalReference;
	private String title;
	private String description;
	private BigDecimal totalAmount;
	private List<ItemDTO> items;
	private CashOutDTO cashOut;

}
