package com.fiap.challenge.tastefood.adapter.driver.formsDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderFormDto {

	private List<ProductFormDto> products;
	private ClientFormDto client;

}
