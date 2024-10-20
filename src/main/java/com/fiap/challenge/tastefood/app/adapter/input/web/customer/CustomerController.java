package com.fiap.challenge.tastefood.app.adapter.input.web.customer;

import com.fiap.challenge.tastefood.app.adapter.input.web.customer.dto.CustomerRequest;
import com.fiap.challenge.tastefood.app.adapter.input.web.customer.dto.CustomerResponse;
import com.fiap.challenge.tastefood.app.adapter.input.web.customer.mapper.CustomerRequestMapper;
import com.fiap.challenge.tastefood.app.adapter.input.web.customer.mapper.CustomerResponseMapper;
import com.fiap.challenge.tastefood.core.domain.Customer;
import com.fiap.challenge.tastefood.core.usecases.customer.CustomerCreateUseCase;
import com.fiap.challenge.tastefood.core.usecases.customer.CustomerListUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerController {

    private final CustomerCreateUseCase customerCreateUseCase;
    private final CustomerListUseCase customerListUseCase;
    private final CustomerRequestMapper customerRequestMapper;
    private final CustomerResponseMapper customerResponseMapper;

    @PostMapping(path = "/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse create(@RequestBody CustomerRequest customerRequest) {
        Customer customer = customerRequestMapper.toCustomer(customerRequest);
        Customer customerSave = customerCreateUseCase.execute(customer);
        return customerResponseMapper.toCustomerResponse(customerSave);
    }

    @GetMapping(path = "/customer")
    public List<CustomerResponse> list(@RequestParam(required = false) String document) {
        List<Customer> customerList = customerListUseCase.execute(document);
        return customerResponseMapper.toCustomer(customerList);
    }

}
