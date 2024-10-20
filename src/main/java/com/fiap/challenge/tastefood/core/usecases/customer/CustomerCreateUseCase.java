package com.fiap.challenge.tastefood.core.usecases.customer;

import com.fiap.challenge.tastefood.core.common.validator.CustomerCreateValidator;
import com.fiap.challenge.tastefood.core.domain.Customer;
import com.fiap.challenge.tastefood.core.gateways.CustomerGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerCreateUseCase {

    private final CustomerGateway customerGateway;
    private final CustomerCreateValidator validator;

    public Customer execute(Customer customer) {
        validator.validate(customer);
        return customerGateway.save(customer);
    }

}