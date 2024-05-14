package com.fiap.challenge.tastefood.adapter.driver.controller;

import com.fiap.challenge.tastefood.adapter.driver.dto.CustomerRequest;
import com.fiap.challenge.tastefood.core.application.dto.CustomerResponse;
import com.fiap.challenge.tastefood.core.application.useCase.customer.CreateCustomerUseCase;
import com.fiap.challenge.tastefood.core.application.useCase.customer.ListCustomersUseCase;
import com.fiap.challenge.tastefood.core.domain.mapper.CustomerRequestMapper;
import com.fiap.challenge.tastefood.core.domain.mapper.CustomerResponseMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final ListCustomersUseCase listCustomersUseCase;
    private final CustomerRequestMapper customerRequestMapper;
    private final CustomerResponseMapper customerResponseMapper;

    @PostMapping(path = "/customer")
    public void create(@RequestBody CustomerRequest request) {
        createCustomerUseCase.execute(customerRequestMapper.map(request));
    }

    @GetMapping(path = "/customer")
    public List<CustomerResponse> list() {
        return customerResponseMapper.map(listCustomersUseCase.execute());
    }

}
