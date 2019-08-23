package com.kw13.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 请求携带AuthToken= 用于校验是否登录
 * create 2018-07-09
 **/
@Component
public class HasAuthFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(HasAuthFilter.class);
    @Value("${interceptor.auth-exclude-uris}")
    private String authExcludeUris;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        String[] urisArray = authExcludeUris.split(";");
        List<String> uris = Arrays.asList(urisArray);
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String s = request.getRequestURI().toString();
        if (uris.contains(s)) {
            return false;
        }
        //urls 需要过滤
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