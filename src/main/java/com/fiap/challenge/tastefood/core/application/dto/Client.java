package com.fiap.challenge.tastefood.core.application.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private String name;
    private int age;
    private String mail;
    private String document;

}
