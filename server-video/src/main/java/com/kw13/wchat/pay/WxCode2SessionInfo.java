package com.kw13.wchat.pay;

/**
 * @Desc 微信登录返回信息
 * @Author yejx
 * @Date 2019/3/11
 */
public class WxCode2SessionInfo {

    private String openid;
    private String sessionKey;
    private String unionId;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    @Override
    public String toString() {
        return "WxCode2SessionInfo{" +
                "openid='" + openid + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", unionId='" + unionId + '\'' +
                '}';
    }
}
