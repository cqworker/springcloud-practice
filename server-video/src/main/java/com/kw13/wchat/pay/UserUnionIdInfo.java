package com.kw13.wchat.pay;

/**
 * Created by cheng on 2019/4/23.
 *获取用户unionid
 */
public class UserUnionIdInfo {
    private String encryptedData;
    private String iv;
    private String signature;

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
