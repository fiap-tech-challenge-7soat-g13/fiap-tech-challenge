package com.fiap.challenge.tastefood.adapter.driver.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CheckoutOrderFormDto {

    private List<ProductRequest> products;
    private CustomerRequest customer;

}
