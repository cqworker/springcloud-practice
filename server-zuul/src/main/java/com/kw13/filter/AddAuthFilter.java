package com.kw13.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 校验通过发放 authToken
 * create 2018-07-09
 **/
@Component
public class AddAuthFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(AddAuthFilter.class);

    @Value("${interceptor.give-auth-uris}")
    private String giveAuthUris;

    @Autowired

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        String[] urisArray = giveAuthUris.split(";");
        List<String> uris = Arrays.asList(urisArray);
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String s = request.getRequestURI().toString();
        if (uris.contains(s)) {
            return false;
        }
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = ctx.getRequest();
        HttpServletResponse httpServletResponse = ctx.getResponse();
        log.info(String.format("%s >>> %s", httpServletRequest.getMethod(), httpServletRequest.getRequestURL().toString()));
        String token = httpServletRequest.getHeader("Authorization");
        if (token == null) {
            log.warn("Authorization-token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("Authorization-token is empty");
            }catch (Exception e){}

            return null;
        }
        log.info("ok");
        return null;
    }
}