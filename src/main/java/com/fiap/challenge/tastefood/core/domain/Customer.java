package com.fiap.challenge.tastefood.core.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Customer {

    private Long id;
    private String name;
    private String email;
    private String document;
    private String sessionToken;
    private String password;

}