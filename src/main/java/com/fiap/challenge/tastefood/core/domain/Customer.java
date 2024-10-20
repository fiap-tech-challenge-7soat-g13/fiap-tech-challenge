package com.fiap.challenge.tastefood.core.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

    private Long id;
    private String name;
    private String email;
    private String document;
    private String password;

}