package com.fiap.challenge.tastefood.app.adapter.input.web.customer.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CustomerResponse {

    private UUID id;
    private String name;
    private String email;
    private String document;

}
