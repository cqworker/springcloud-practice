package com.kw13.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Desc 用户登录信息获取 工具类
 * @Date 2019/3/11
 */
@Component
public class LoginInfoUtil {

    private static String userSysidKey;
    private static String IP = "ip";
    private static String UNKNOWN = "unknown";

    @Value("${JWT.request-user-key}")
    public void initKeyUserSysidKey(String key) {
        userSysidKey = key;
    }

    public class LoginInfo {
        private Map<String,String> infoMap;

        public String getUserSysid() {
            if (this.infoMap.containsKey(userSysidKey)) {
                return infoMap.get(userSysidKey);
            }
            return "";
        }

        public String getIp() {
            if (this.infoMap.containsKey(IP)) {
                return infoMap.get(IP);
            }
            return "";
        }

        private LoginInfo(Map<String,String> infoMap) {
            this.infoMap = infoMap;
        }
    }

    /**
     * 获取登录用户信息
     *
     * @return 登录用户权限Map
     */
    public static LoginInfo getLoginInfo(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        Map<String,String> infoMap = new HashMap<>(5);
        infoMap.put(userSysidKey, (String) request.getAttribute(userSysidKey));
        infoMap.put(IP, getIpAddress(request));
        return new LoginInfoUtil().new LoginInfo(infoMap);
    }

    /**
     * 获取登录用户ip
     * @param request 请求内容
     * @return ip
     */
    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
