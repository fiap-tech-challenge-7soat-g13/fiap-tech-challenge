package com.fiap.challenge.tastefood.core.domain.validation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Optional;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Validation {

    private String message;

    public static Validation notBlank(String value, String message, Object... params) {
        return assertFalse(StringUtils.isBlank(value), message, params);
    }

    public static Validation notNull(Object value, String message, Object... params) {
        return assertFalse(value == null, message, params);
    }

    public static Validation notEmpty(Collection<?> value, String message, Object... params) {
        return assertFalse(CollectionUtils.isEmpty(value), message, params);
    }

    public static <T extends Comparable<T>> Validation greaterThan(T value, T min, String message, Object... params) {
        return assertFalse(value != null && value.compareTo(min) <= 0, message, params);
    }

    public static Validation assertTrue(boolean condition, String message, Object... params) {
        return assertFalse(!condition, message, params);
    }

    public static Validation assertFalse(boolean condition, String message, Object... params) {
        return new Validation(condition ? String.format(message, params) : null);
    }

    public Optional<String> getMessage() {
        return Optional.ofNullable(message);
    }

}
