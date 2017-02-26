package com.jmonsinjon.header.aspect;

import com.jmonsinjon.header.context.HeaderHolder;
import com.jmonsinjon.header.settings.HeaderSettings;
import org.apache.commons.logging.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by jmonsinjon on 25/02/17.
 */
@Aspect
@Component
public class HeaderAspect {

    private static final Log log = org.apache.commons.logging.LogFactory
            .getLog(HeaderAspect.class);

    private HeaderSettings headerSettings;

    @Autowired
    public HeaderAspect(HeaderSettings headerSettings) {
        this.headerSettings = headerSettings;
    }

    @Around("@annotation(ExtractHeader)")
    public Object anyExtractHeaderAnnotated(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("<----- Interception of method " + joinPoint.getSignature());

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        headerSettings.getHeaders().forEach(x -> {
            if (request.getHeader(x) != null) {
                HeaderHolder.addHeader(x, request.getHeader(x));
            }
        });

        log.info("<----- Intercepted and stored headers : " + HeaderHolder.getHeaders());

        Object response = joinPoint.proceed();

        log.info("Clear local headers memory");
        HeaderHolder.getHeaders().clear();
        return response;
    }

    @Around("@annotation(ExtractCookie)")
    public Object anyExtractCookieAnnotated(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("<----- Interception of method " + joinPoint.getSignature());

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        headerSettings.getCookies().forEach(x -> {
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals(x)) {
                        HeaderHolder.addHeader(x, request.getHeader(x));
                        break;
                    }
                }
            }
        });

        log.info("<----- Intercepted and stored cookies : " + HeaderHolder.getHeaders());

        Object response = joinPoint.proceed();

        log.info("Clear local headers memory");
        HeaderHolder.getHeaders().clear();
        return response;
    }
}
