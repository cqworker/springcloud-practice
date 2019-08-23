package com.kw13.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * 请求携带?token= 用于防重,等幂
 * create 2018-07-09
 **/
@Component
public class HasTokenFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(HasTokenFilter.class);

    @Value("${interceptor.token-uris}")
    private String tokenExcludeUris;

    @Override
    public String filterType() {
        //执行时机 可选pre routing post
        return "pre";
    }

    @Override
    public int filterOrder() {
        //值越小优先级越高
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //是否执行
        String[] urisArray = tokenExcludeUris.split(";");
        List<String> uris = Arrays.asList(urisArray);
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String s = request.getRequestURI().toString();
        if (uris.contains(s)) {
            return true;
        }
        //urls 需要过滤
        return false;
    }

    @Override
    public Object run() {

        //redis中记录token,存在说明执行过,设置有效期
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
//        /doctor-ser/user/login
        log.info("{}-----------{}",request.getMethod(), request.getRequestURI().toString());
//        http://localhost:8769/doctor-ser/user/login
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        if (accessToken == null) {
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            } catch (Exception e) {
            }

            return null;
        }


        log.info("ok");
        return null;
    }
}