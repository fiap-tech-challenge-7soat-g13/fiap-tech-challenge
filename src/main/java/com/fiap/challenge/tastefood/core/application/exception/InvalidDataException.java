package com.fiap.challenge.tastefood.core.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class InvalidDataException extends RuntimeException {

    private List<String> messages;

}
