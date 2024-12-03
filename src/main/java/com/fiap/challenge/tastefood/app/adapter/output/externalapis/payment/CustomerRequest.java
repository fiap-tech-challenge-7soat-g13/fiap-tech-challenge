package com.fiap.challenge.tastefood.app.adapter.output.externalapis.payment;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CustomerRequest {

    private UUID id;
    private String name;
    private String email;
    private String document;

}
