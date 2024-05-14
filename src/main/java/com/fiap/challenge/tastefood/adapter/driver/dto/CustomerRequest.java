package com.fiap.challenge.tastefood.adapter.driver.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

	private String name;
	private String email;
	private String document;

}