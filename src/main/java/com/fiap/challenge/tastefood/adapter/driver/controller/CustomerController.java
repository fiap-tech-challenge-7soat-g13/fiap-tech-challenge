package com.fiap.challenge.tastefood.adapter.driver.controller;

import com.fiap.challenge.tastefood.core.application.dto.CustomerRequest;
import com.fiap.challenge.tastefood.core.application.dto.CustomerResponse;
import com.fiap.challenge.tastefood.core.application.useCase.customer.CustomerCreateUseCase;
import com.fiap.challenge.tastefood.core.application.useCase.customer.CustomerListUseCase;
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

    @PostMapping(path = "/customer")
    public void create(@RequestBody CustomerRequest customer) {
        customerCreateUseCase.execute(customer);
    }

    @GetMapping(path = "/customer")
    public ResponseEntity<?> list(@RequestParam(required = false) String document) {
        List<CustomerResponse> customerList = customerListUseCase.execute(document);

        if (StringUtils.isBlank(document))
            return ResponseEntity
                    .status(OK)
                    .body(customerList);
        else {
            if (customerList.isEmpty())
                return ResponseEntity
                        .status(NO_CONTENT)
                        .build();
            else
                return ResponseEntity
                        .status(OK)
                        .body(customerList.get(0));
        }
    }

}
