package com.fiap.challenge.tastefood.core.application.validator;

import com.fiap.challenge.tastefood.core.application.util.validation.Validation;
import com.fiap.challenge.tastefood.core.application.util.validation.Validator;
import com.fiap.challenge.tastefood.core.application.vo.CustomerInput;
import com.fiap.challenge.tastefood.core.domain.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerCreateValidator {

    private final CustomerRepository customerRepository;

    public void validate(CustomerInput customerInput) {

        Validator validator = new Validator();

        validator.add(Validation.notBlank(customerInput.getName(), "É obrigatório informar o nome"));
        validator.add(Validation.notBlank(customerInput.getEmail(), "É obrigatório informar o e-mail"));
        validator.add(Validation.notInvalidEmail(customerInput.getEmail(), "O e-mail informado é inválido"));
        validator.add(Validation.notBlank(customerInput.getDocument(), "É obrigatório informar o documento"));
        validator.add(Validation.notInvalidDocument(customerInput.getDocument(), "O documento informado é inválido"));
        validator.add(Validation.assertFalse(documentAlreadyExists(customerInput.getDocument()), "Já existe um cliente com o documento '%s'", customerInput.getDocument()));

        validator.assertEmptyMessages();
    }

    private boolean documentAlreadyExists(String document) {
        return document != null && !customerRepository.findByDocument(document).isEmpty();
    }

}
