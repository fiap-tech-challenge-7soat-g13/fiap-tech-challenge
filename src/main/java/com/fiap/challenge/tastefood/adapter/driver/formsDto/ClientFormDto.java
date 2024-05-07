package com.fiap.challenge.tastefood.adapter.driver.formsDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class ClientFormDto {

	private String name;
	private int age;
	private String mail;
	private String document;

}