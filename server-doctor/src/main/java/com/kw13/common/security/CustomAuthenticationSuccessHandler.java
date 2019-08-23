package com.kw13.common.security;

import com.google.gson.Gson;
import com.kw13.common.BaseResponse;
import com.kw13.common.ResponseGenerator;
import com.kw13.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * 认证成功处理器
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenUtil jwtTokenUtil;

    CustomAuthenticationSuccessHandler(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String tokenStr = jwtTokenUtil.getTokenStr(httpServletRequest.getParameter("userId"));
        HashMap<String,String> data = new HashMap<>();
        data.put("Authorization",tokenStr);
        BaseResponse baseResponse = ResponseGenerator.genSuccessResult(data);
        baseResponse.setMsg("登录成功");

        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        httpServletResponse.getWriter().write(new Gson().toJson(baseResponse));

    }

}