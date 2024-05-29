package com.fiap.challenge.tastefood.adapter.driver.controller;

import com.fiap.challenge.tastefood.adapter.driver.dto.CustomerRequest;
import com.fiap.challenge.tastefood.adapter.driver.dto.CustomerResponse;
import com.fiap.challenge.tastefood.adapter.driver.mapper.CustomerMapper;
import com.fiap.challenge.tastefood.core.application.useCase.customer.CustomerCreateUseCase;
import com.fiap.challenge.tastefood.core.application.useCase.customer.CustomerListUseCase;
import com.fiap.challenge.tastefood.core.application.vo.CustomerInput;
import com.fiap.challenge.tastefood.core.application.vo.CustomerOutput;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
public class CustomerController {

    private final CustomerCreateUseCase customerCreateUseCase;
    private final CustomerListUseCase customerListUseCase;
    private final CustomerMapper customerMapper;

    @PostMapping(path = "/customer")
    public void create(@RequestBody CustomerRequest customerRequest) {
        CustomerInput customerInput = customerMapper.toCustomerInput(customerRequest);
        customerCreateUseCase.execute(customerInput);
    }

    @GetMapping(path = "/customer")
    public ResponseEntity<?> list(@RequestParam(required = false) String document) {
        List<CustomerOutput> customerList = customerListUseCase.execute(document);
        List<CustomerResponse> customerResponse = customerMapper.toCustomerResponse(customerList);

        if (StringUtils.isBlank(document))
            return ResponseEntity
                    .status(OK)
                    .body(customerResponse);
        else {
            if (customerResponse.isEmpty())
                return ResponseEntity
                        .status(NO_CONTENT)
                        .build();
            else
                return ResponseEntity
                        .status(OK)
                        .body(customerResponse.get(0));
        }
    }

}
