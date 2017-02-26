package com.jmonsinjon.header.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jmonsinjon on 26/02/17.
 */
@Component
@ConfigurationProperties(prefix = "interceptor")
public class HeaderSettings {

    private List<String> headers = new ArrayList<>();
    private List<String> cookies = new ArrayList<>();

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<String> getCookies() {
        return cookies;
    }

    public void setCookies(List<String> cookies) {
        this.cookies = cookies;
    }
}
