package com.jmonsinjon.header.web;

import com.jmonsinjon.header.aspect.ExtractCookie;
import com.jmonsinjon.header.aspect.ExtractHeader;
import com.jmonsinjon.header.service.HeaderService;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jmonsinjon on 23/02/17.
 */
@RestController
@RequestMapping(value = "/")
public class FirstController {
    private static final Log log = org.apache.commons.logging.LogFactory
            .getLog(FirstController.class);

    private HeaderService headerService;

    @Autowired
    public FirstController(HeaderService headerService) {
        this.headerService = headerService;
    }

    @RequestMapping(value = "/first-call-header")
    @ExtractHeader({"site-header", "site-header-2"})
    public void firstCallHeader() {
        headerService.firstCall();
    }

    @RequestMapping(value = "/first-call-cookie")
    @ExtractCookie({"cookie_1", "cookie_2"})
    public void firstCallCookie() {
        headerService.firstCall();
    }
}
