package com.kw13.common.interceptor;

import com.kw13.account.mapper.TKwAccountMapper;
import com.kw13.util.JwtTokenUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @Desc  拦截请求做登录验证 接口验证
 * @Date 2019/3/8
 * */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final Logger log = LogManager.getLogger(getClass());

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private TKwAccountMapper userDao;
    @Value("${JWT.request-user-key}")
    private String userKey;

    @Value("${JWT.token-prefix}")
    private String tokenPrefix;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        boolean flag = false;

        flag = true;

//        String requestUri = httpServletRequest.getRequestURI();
//        String token = httpServletRequest.getHeader("Authorization");
//        if (token.startsWith(tokenPrefix)) {
//            token = token.replaceFirst(tokenPrefix, "");
//            Map<String, String> infoMap = null;
//            try {
//                infoMap = jwtTokenUtil.parseJWT(token);
//            } catch (Exception e) {
//                httpServletResponse.setCharacterEncoding("UTF-8");
//                httpServletResponse.setContentType("application/json; charset=utf-8");
//                String json = "{\"result\": \"FAIL\",\"retMsg\": \"服务器内部jwt错误\"}";
//                log.info(json);
//                PrintWriter out = null;
//                try {
//                    out = httpServletResponse.getWriter();
//                    out.append(json);
//                    return false;
//                } catch (IOException e1) {
//                    log.info("jwt解析token异常");
//                } finally {
//                    if (out != null) {
//                        out.close();
//                    }
//                }
//            }
//            log.info("解析token获取信息为:{}", infoMap);
//            if (userDao.selectById(infoMap.get(userKey)) != null) {
////                获取到userid后放入request域中
//                httpServletRequest.setAttribute(userKey, infoMap.get(userKey));
//                flag = true;
//            }
//        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

    }
}
