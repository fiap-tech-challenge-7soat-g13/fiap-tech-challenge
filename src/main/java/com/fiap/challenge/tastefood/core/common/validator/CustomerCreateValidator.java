package com.fiap.challenge.tastefood.core.common.validator;

import com.fiap.challenge.tastefood.core.common.util.validation.Validation;
import com.fiap.challenge.tastefood.core.common.util.validation.Validator;
import com.fiap.challenge.tastefood.core.domain.Customer;
import com.fiap.challenge.tastefood.core.gateways.CustomerGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerCreateValidator {

    private final CustomerGateway customerGateway;

    public void validate(Customer customer) {

        Validator validator = new Validator();

        validator.add(Validation.notBlank(customer.getName(), "É obrigatório informar o nome"));
        validator.add(Validation.notBlank(customer.getEmail(), "É obrigatório informar o e-mail"));
        validator.add(Validation.notInvalidEmail(customer.getEmail(), "O e-mail informado é inválido"));
        validator.add(Validation.assertFalse(emailAlreadyExists(customer.getEmail()), "Já existe um cliente com o e-mail '%s'", customer.getEmail()));
        validator.add(Validation.notBlank(customer.getDocument(), "É obrigatório informar o documento"));
        validator.add(Validation.notInvalidDocument(customer.getDocument(), "O documento informado é inválido"));
        validator.add(Validation.assertFalse(documentAlreadyExists(customer.getDocument()), "Já existe um cliente com o documento '%s'", customer.getDocument()));
        validator.add(Validation.notBlank(customer.getPassword(), "É obrigatório informar a senha"));

        if (customer.getPassword() != null) {
            validator.add(Validation.assertFalse(customer.getPassword().length() < 8, "A senha deve conter ao menos 8 caracteres"));
            validator.add(Validation.assertFalse(weakPassword(customer.getPassword()), "A senha deve conter letras maiúsculas, minúsculas, números e caracteres especiais"));
        }

        validator.assertEmptyMessages();
    }

    private boolean documentAlreadyExists(String document) {
        return document != null && !customerGateway.findByDocument(document).isEmpty();
    }

    private boolean emailAlreadyExists(String email) {
        return email != null && !customerGateway.findByEmail(email).isEmpty();
    }

    private static boolean weakPassword(String password) {
        String pattern = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*";
        return !password.matches(pattern);
    }

}
