package com.fiap.challenge.tastefood.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Customer {

    private UUID id;
    private String name;
    private String email;
    private String document;

}