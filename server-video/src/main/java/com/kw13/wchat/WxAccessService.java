package com.kw13.wchat;


import com.kw13.wchat.pay.WxCode2SessionInfo;

/**
 * Created by cheng on 2019/5/21.
 */
public interface WxAccessService {
    // 小程序 从redis中获取 更新由唯一的小程序(商保平台负责)
    String getTokenFormRedis();

    // 用户
    WxCode2SessionInfo getSessionCode(String code) throws Exception;
    String getSessionKey(String openid);
    String updateSesionKey(WxCode2SessionInfo wxSessionInfo);

}
