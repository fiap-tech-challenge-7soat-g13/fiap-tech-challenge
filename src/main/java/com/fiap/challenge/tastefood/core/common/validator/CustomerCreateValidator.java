package com.fiap.challenge.tastefood.core.common.validator;

import com.fiap.challenge.tastefood.app.adapter.output.persistence.repository.CustomerRepository;
import com.fiap.challenge.tastefood.core.common.util.validation.Validation;
import com.fiap.challenge.tastefood.core.common.util.validation.Validator;
import com.fiap.challenge.tastefood.core.domain.Customer;
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
        validator.add(Validation.notInvalidEmail(customer.getEmail(), "O e-mail informado é inválido"));
        validator.add(Validation.notBlank(customer.getDocument(), "É obrigatório informar o documento"));
        validator.add(Validation.notInvalidDocument(customer.getDocument(), "O documento informado é inválido"));
        validator.add(Validation.assertFalse(documentAlreadyExists(customer.getDocument()), "Já existe um cliente com o documento '%s'", customer.getDocument()));

        validator.assertEmptyMessages();
    }

    private boolean documentAlreadyExists(String document) {
        return document != null && !customerRepository.findByDocument(document).isEmpty();
    }

}
