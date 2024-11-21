package com.fiap.challenge.tastefood.core.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.URI;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Strings {

    public static boolean isValidUrl(String value) {
        try {
            new URI(value).toURL();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
