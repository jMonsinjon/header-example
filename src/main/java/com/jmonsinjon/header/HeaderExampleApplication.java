package com.jmonsinjon.header;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class HeaderExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeaderExampleApplication.class, args);
    }
}
