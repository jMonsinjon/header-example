package com.jmonsinjon.header.context;

import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jmonsinjon on 23/02/17.
 */
public class HeaderHolder {

    private static final ThreadLocal<Map<String, String>> HEADERS = new NamedThreadLocal<>(
            "Header Context");

    public static void addHeader(String headerKey, String headerValue) {
        if (HEADERS.get() == null) {
            HEADERS.set(new HashMap<>());
        }

        HEADERS.get().put(headerKey, headerValue);
    }

    public static Map<String, String> getHeaders() {
        return HEADERS.get();
    }

    public static String getHeader(String headerKey) {
        return HEADERS.get().get(headerKey);
    }
}
