package com.kw13.wchat.pay;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * @Desc 微信支付统一下单
 * @Author yejx
 * @Date 2019/3/14
 */
@JacksonXmlRootElement(localName = "xml")
public class WechatPayXml {

    @JacksonXmlProperty(localName = "appid")
    private String appid;
    @JacksonXmlProperty(localName = "mch_id")
    private String mch_id;
    @JacksonXmlProperty(localName = "nonce_str")
    private String nonce_str;
    @JacksonXmlProperty(localName = "body")
    private String body;
    @JacksonXmlProperty(localName = "out_trade_no")
    private String out_trade_no;
    @JacksonXmlProperty(localName = "total_fee")
    private String total_fee;
    @JacksonXmlProperty(localName = "spbill_create_ip")
    private String spbill_create_ip;
    @JacksonXmlProperty(localName = "notify_url")
    private String notify_url;
    @JacksonXmlProperty(localName = "trade_type")
    private String trade_type;
    @JacksonXmlProperty(localName = "openid")
    private String openid;
    @JacksonXmlProperty(localName = "sign")
    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "WechatPayXml{" +
                "appid='" + appid + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", body='" + body + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", total_fee='" + total_fee + '\'' +
                ", spbill_create_ip='" + spbill_create_ip + '\'' +
                ", notify_url='" + notify_url + '\'' +
                ", trade_type='" + trade_type + '\'' +
                ", openid='" + openid + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
