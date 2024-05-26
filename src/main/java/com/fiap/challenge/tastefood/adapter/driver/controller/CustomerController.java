package com.fiap.challenge.tastefood.adapter.driver.controller;

import com.fiap.challenge.tastefood.core.application.dto.CustomerRequest;
import com.fiap.challenge.tastefood.core.application.dto.CustomerResponse;
import com.fiap.challenge.tastefood.core.application.useCase.customer.CustomerCreateUseCase;
import com.fiap.challenge.tastefood.core.application.useCase.customer.CustomerListUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerController {

    private final CustomerCreateUseCase customerCreateUseCase;
    private final CustomerListUseCase customerListUseCase;

    @PostMapping(path = "/customer")
    public void create(@RequestBody CustomerRequest customer) {
        customerCreateUseCase.execute(customer);
    }

    @GetMapping(path = "/customer")
    public List<CustomerResponse> list(@RequestParam(required = false) String document) {
        return customerListUseCase.execute(document);
    }

}
