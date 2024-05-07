package com.fiap.challenge.tastefood.core.applications.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class Client {

    private String name;
    private int age;
    private String mail;
    private String document;

}
