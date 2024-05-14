package com.fiap.challenge.tastefood.core.domain.validation;

import com.fiap.challenge.tastefood.adapter.driver.infra.CustomerGateway;
import com.fiap.challenge.tastefood.core.domain.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateCustomerValidator {

    private final CustomerGateway customerGateway;

    public void validate(Customer customer) {

        Validator validator = new Validator();

        validator.add(Validation.assertFalse(customerGateway.findByDocument(customer.getDocument()).isPresent(), "Cliente já possui cadastro"));

        validator.assertEmptyMessages();
    }

}
