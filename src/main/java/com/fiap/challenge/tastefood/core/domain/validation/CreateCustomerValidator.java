package com.fiap.challenge.tastefood.core.domain.validation;

import com.fiap.challenge.tastefood.core.domain.entity.Customer;
import com.fiap.challenge.tastefood.core.domain.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateCustomerValidator {

    private final CustomerRepository customerRepository;

    public void validate(Customer customer) {

        Validator validator = new Validator();

        validator.add(Validation.assertFalse(customerRepository.existsByDocument(customer.getDocument()), "Cliente já possui cadastro"));

        validator.assertEmptyMessages();
    }

}
