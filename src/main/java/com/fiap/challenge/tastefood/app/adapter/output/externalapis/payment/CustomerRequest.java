package com.fiap.challenge.tastefood.app.adapter.output.externalapis.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {

    private Long id;
    private String name;
    private String email;
    private String document;

}
