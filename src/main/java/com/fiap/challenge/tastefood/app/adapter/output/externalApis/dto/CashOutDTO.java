package com.fiap.challenge.tastefood.app.adapter.output.externalApis.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CashOutDTO {

	private BigDecimal amount;

}
