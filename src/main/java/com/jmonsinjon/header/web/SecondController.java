package com.jmonsinjon.header.web;

import com.jmonsinjon.header.service.HeaderService;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jmonsinjon on 26/02/17.
 */
@RestController
@RequestMapping(value = "/")
public class SecondController {

    private static final Log log = org.apache.commons.logging.LogFactory
            .getLog(SecondController.class);

    private HeaderService headerService;

    @Autowired
    public SecondController(HeaderService headerService) {
        this.headerService = headerService;
    }

    @RequestMapping(value = "/second-call")
    public void secondCall(
            @RequestHeader(value = "header_1", required = false) String header_1,
            @RequestHeader(value = "header_2", required = false) String header_2) {
        log.info("-----> Second call headers {header_1:" + header_1 + "; header_2:" + header_2 + "}");
        headerService.secondCall();
    }
}
