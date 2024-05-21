package com.fiap.challenge.tastefood.core.domain.util;

import java.net.URI;

public class StringUtils {

    public static boolean isValidUrl(String value) {
        try {
            new URI(value).toURL();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
