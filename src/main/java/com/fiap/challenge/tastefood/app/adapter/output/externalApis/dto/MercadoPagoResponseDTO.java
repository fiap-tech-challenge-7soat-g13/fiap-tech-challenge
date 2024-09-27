package com.fiap.challenge.tastefood.app.adapter.output.externalApis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MercadoPagoResponseDTO {

	private String qrData;

}
