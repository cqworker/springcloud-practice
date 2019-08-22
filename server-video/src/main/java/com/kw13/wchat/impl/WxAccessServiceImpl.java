package com.kw13.wchat.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kw13.util.HttpUtil;
import com.kw13.wchat.WxAccessService;
import com.kw13.wchat.pay.WxCode2SessionInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by cheng on 2019/5/21.
 */
@Service
public class WxAccessServiceImpl implements WxAccessService {
    private final Logger log = LogManager.getLogger(getClass());

    @Autowired
    private RedisTemplate<String, String> template;
    @Autowired
    private Environment env;
    @Autowired
    private HttpUtil httpUtil;


    @Value("${wechat.config.accesstoken_redis_key}")
    private String accessTokenRedisKey;
    @Value("${wechat.api.get-access-token}")
    private String accessTokenUrl;
    @Value("${wechat.api.jscode2session}")
    private String jsCode2session;


    @Value("${wechat.account.appid}")
    private String appId;
    @Value("${wechat.account.appsecret}")
    private String appSecret;
    @Value("${spring.application.name}")
    private String appName;

    @Override
    public String getTokenFormRedis() {
//        String access_token = redisService.getStrHashValue(accessTokenRedisKey, "access_token");
        String access_token = "access_token";
        return access_token;
    }

    @Override
    public WxCode2SessionInfo getSessionCode(String code) throws Exception {
        log.info("code授权{}", code);
        String resStr = null;
        try {
            resStr = httpUtil.getForString(jsCode2session, "appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code");
        } catch (IOException e) {
            log.info("获取session出错: {}", e);
            return null;
        }
        log.info("code授权响应:{}", resStr);
        if (resStr.contains("errcode")) {
            String errcode = new JsonParser().parse(resStr).getAsJsonObject().get("errcode").getAsString();
            String errmsg = new JsonParser().parse(resStr).getAsJsonObject().get("errmsg").getAsString();
            throw new Exception("根据code获取openid,sessionkey异常:"+errcode+errmsg);
        }
        JsonObject jsonObj = new JsonParser().parse(resStr).getAsJsonObject();
        WxCode2SessionInfo wxCode2SessionInfo = new WxCode2SessionInfo();
        wxCode2SessionInfo.setOpenid(jsonObj.get("openid").getAsString());
        wxCode2SessionInfo.setSessionKey(jsonObj.get("session_key").getAsString());
        // unionId不一定存在 关注过小程序关联的公众号,或者授权过才会获取到
        if (jsonObj.has("unionid")) {
            wxCode2SessionInfo.setUnionId(jsonObj.get("unionid").getAsString());
        }
        updateSesionKey(wxCode2SessionInfo);
        return wxCode2SessionInfo;
    }

    @Override
    public String getSessionKey(String openid) {
//        return redisService.getStrValue(appName + openid);
        return "";
    }

    @Override
    public String updateSesionKey(WxCode2SessionInfo wxSessionInfo) {
//        redisService.setValue(appName + wxSessionInfo.getOpenid(), wxSessionInfo.getSessionKey());
        return wxSessionInfo.getSessionKey();
    }
}
