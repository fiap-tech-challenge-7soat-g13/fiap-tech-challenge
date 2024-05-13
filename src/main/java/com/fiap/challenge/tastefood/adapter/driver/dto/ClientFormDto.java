package com.fiap.challenge.tastefood.adapter.driver.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientFormDto {

	private String name;
	private int age;
	private String mail;
	private String document;

}