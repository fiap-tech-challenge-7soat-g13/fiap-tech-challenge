package com.fiap.challenge.tastefood.core.domain.validation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Optional;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Validation {

    private String message;

    public static Validation notBlank(String value, String message) {
        return assertFalse(StringUtils.isBlank(value), message);
    }

    public static Validation notNull(Object value, String message) {
        return assertFalse(value == null, message);
    }

    public static Validation notEmpty(Collection<?> value, String message) {
        return assertFalse(CollectionUtils.isEmpty(value), message);
    }

    public static <T extends Comparable<T>> Validation greaterThan(T value, T min, String message) {
        return assertFalse(value != null && value.compareTo(min) <= 0, message);
    }

    public static Validation assertTrue(boolean condition, String message) {
        return assertFalse(!condition, message);
    }

    public static Validation assertFalse(boolean condition, String message) {
        return new Validation(condition ? message : null);
    }

    public Optional<String> getMessage() {
        return Optional.ofNullable(message);
    }

}
