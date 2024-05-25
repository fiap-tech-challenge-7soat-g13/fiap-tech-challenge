package com.fiap.challenge.tastefood.core.domain.validation;

import com.fiap.challenge.tastefood.core.domain.entity.Customer;
import com.fiap.challenge.tastefood.core.domain.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerCreateValidator {

    private final CustomerRepository customerRepository;

    public void validate(Customer customer) {

        Validator validator = new Validator();

        validator.add(Validation.notBlank(customer.getName(), "É obrigatório informar o nome"));
        validator.add(Validation.notBlank(customer.getEmail(), "É obrigatório informar o e-mail"));
        validator.add(Validation.notBlank(customer.getDocument(), "É obrigatório informar o documento"));

        if (customer.getDocument() != null) {
            validator.add(Validation.assertFalse(customerRepository.existsByDocument(customer.getDocument()), "Já existe um cliente com o documento '%s'", customer.getDocument()));
        }

        validator.assertEmptyMessages();
    }

}
