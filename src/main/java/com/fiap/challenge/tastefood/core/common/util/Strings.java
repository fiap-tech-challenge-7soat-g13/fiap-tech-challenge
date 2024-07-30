package com.fiap.challenge.tastefood.core.common.util;

import br.com.caelum.stella.validation.CPFValidator;

import java.net.URI;
import java.util.regex.Pattern;

public class Strings {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

    public static boolean isValidUrl(String value) {
        try {
            new URI(value).toURL();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidEmail(String value) {
        return EMAIL_PATTERN.matcher(value).matches();
    }

    public static boolean isValidDocument(String value) {
        return new CPFValidator().invalidMessagesFor(value).isEmpty();
    }

}
