package com.jmonsinjon.header.feign;

import feign.RequestLine;

/**
 * Created by jmonsinjon on 26/02/17.
 */

public interface SecondClient {

    @RequestLine("GET /second-call")
    String getSecondCall();
}
