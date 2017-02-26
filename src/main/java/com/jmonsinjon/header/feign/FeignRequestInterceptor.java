package com.jmonsinjon.header.feign;

import com.jmonsinjon.header.context.HeaderHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.logging.Log;

/**
 * Created by jmonsinjon on 26/02/17.
 */
public class FeignRequestInterceptor implements RequestInterceptor {

    private static final Log log = org.apache.commons.logging.LogFactory
            .getLog(FeignRequestInterceptor.class);

    @Override
    public void apply(RequestTemplate template) {

        log.info("-----> Automatically added headers to feign client");
        HeaderHolder.getHeaders().entrySet()
                .forEach(x -> template.header(x.getKey(), x.getValue()));
    }
}
