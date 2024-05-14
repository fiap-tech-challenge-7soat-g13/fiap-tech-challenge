package com.fiap.challenge.tastefood.core.application.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private String name;
    private String email;
    private String document;

}
