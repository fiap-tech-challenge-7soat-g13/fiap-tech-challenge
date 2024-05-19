package com.fiap.challenge.tastefood.core.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {

    private Long id;
    private String name;
    private String email;
    private String document;

}
