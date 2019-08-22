package com.kw13.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Desc 接口拦截器 拦截请求是否已登录(是否携带token)
 * @Date 2019/3/8
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final Logger log = LogManager.getLogger(getClass());


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        boolean flag = false;
        String requestUri = httpServletRequest.getRequestURI();
        String token = httpServletRequest.getHeader("Authorization");
        if (token != null) {
            return true;
        } else {
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json; charset=utf-8");
            String json;

            json = "{\"result\": false,\"code\": 000001,\"msg\": \"用户未登录\",\"data\": {\"url\":"+requestUri+"}}";
            log.info("请求路径未登录"+json);
            PrintWriter out = null;
            try {
                out = httpServletResponse.getWriter();
                out.append(json);
            } catch (IOException e) {
                log.info("io异常");
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

    }
}
