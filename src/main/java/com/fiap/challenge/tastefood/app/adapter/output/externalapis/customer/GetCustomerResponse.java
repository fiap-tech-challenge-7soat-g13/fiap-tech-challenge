package com.fiap.challenge.tastefood.app.adapter.output.externalapis.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
class GetCustomerResponse {

    private Long id;
    private String name;
    private String email;
    private String document;

}
