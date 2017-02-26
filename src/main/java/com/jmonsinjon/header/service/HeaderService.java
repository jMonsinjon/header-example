package com.jmonsinjon.header.service;

import com.jmonsinjon.header.feign.FeignRequestInterceptor;
import com.jmonsinjon.header.feign.SecondClient;
import feign.Feign;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;

/**
 * Created by jmonsinjon on 23/02/17.
 */
@Service
public class HeaderService {

    private static final Log log = org.apache.commons.logging.LogFactory
            .getLog(HeaderService.class);

    public void firstCall() {
        log.info("First Call !");

        SecondClient client = Feign.builder()
                .requestInterceptor(new FeignRequestInterceptor())
                .target(SecondClient.class, "http://localhost:8080");
        client.getSecondCall();
    }

    public void secondCall() {
        log.info("Second Call !");
    }
}
