package com.fiap.challenge.tastefood.core.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class InvalidDataException extends RuntimeException {

    private List<String> messages;

    public InvalidDataException(String message) {
        this.messages = List.of(message);
    }

}
