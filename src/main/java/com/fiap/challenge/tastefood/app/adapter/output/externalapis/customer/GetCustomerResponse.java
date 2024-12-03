package com.fiap.challenge.tastefood.app.adapter.output.externalapis.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
class GetCustomerResponse {

    private UUID id;
    private String name;
    private String email;
    private String document;

}
