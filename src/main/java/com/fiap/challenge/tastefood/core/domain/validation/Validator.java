package com.fiap.challenge.tastefood.core.domain.validation;

import com.fiap.challenge.tastefood.core.domain.exception.InvalidDataException;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    private final List<String> messages = new ArrayList<>();

    public void add(Validation validation) {
        validation.getMessage().ifPresent(messages::add);
    }

    public void assertEmptyMessages() {
        if (!messages.isEmpty()) {
            throw new InvalidDataException(messages);
        }
    }

}
