package com.fiap.challenge.tastefood.app.adapter.input.web.customer;

import com.fiap.challenge.tastefood.app.adapter.input.web.customer.dto.CustomerRequest;
import com.fiap.challenge.tastefood.app.adapter.input.web.customer.dto.CustomerResponse;
import com.fiap.challenge.tastefood.app.adapter.input.web.customer.mapper.CustomerRequestMapper;
import com.fiap.challenge.tastefood.app.adapter.input.web.customer.mapper.CustomerResponseMapper;
import com.fiap.challenge.tastefood.core.domain.Customer;
import com.fiap.challenge.tastefood.core.useCases.customer.CustomerCreateUseCase;
import com.fiap.challenge.tastefood.core.useCases.customer.CustomerListUseCase;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
public class CustomerController {

    private final CustomerCreateUseCase customerCreateUseCase;
    private final CustomerListUseCase customerListUseCase;
    private final CustomerRequestMapper customerRequestMapper;
    private final CustomerResponseMapper customerResponseMapper;

    @PostMapping(path = "/customer")
    public ResponseEntity<?> create(@RequestBody CustomerRequest customerRequest) {
        try {
            Customer customer = customerRequestMapper.toCustomer(customerRequest);
            Customer customerSave = customerCreateUseCase.execute(customer);
            return ResponseEntity
                    .status(CREATED)
                    .body(customerResponseMapper.toCustomerResponse(customerSave));
        } catch (Exception e) {
            return ResponseEntity
                    .status(BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping(path = "/customer")
    public ResponseEntity<?> list(@RequestParam(required = false) String document) {
        List<Customer> customerList = customerListUseCase.execute(document);
        List<CustomerResponse> customerResponse = customerResponseMapper.toCustomer(customerList);

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
