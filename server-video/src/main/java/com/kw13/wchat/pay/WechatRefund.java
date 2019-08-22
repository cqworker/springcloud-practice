package com.kw13.wchat.pay;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * @Desc 微信支付退款申请报文
 * @Author yejx
 * @Date 2019/3/14
 */
@JacksonXmlRootElement(localName = "xml")
public class WechatRefund {

    @JacksonXmlProperty(localName = "appid")
    private String appid;
    @JacksonXmlProperty(localName = "mch_id")
    private String mch_id;
    @JacksonXmlProperty(localName = "nonce_str")
    private String nonce_str;
    @JacksonXmlProperty(localName = "out_trade_no")
    private String out_trade_no;
    @JacksonXmlProperty(localName = "out_refund_no")
    private String out_refund_no;
    @JacksonXmlProperty(localName = "refund_fee")
    private String refund_fee;
    @JacksonXmlProperty(localName = "total_fee")
    private String total_fee;
    @JacksonXmlProperty(localName = "transaction_id")
    private String transaction_id;
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

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }


    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "WechatRefund{" +
                "appid='" + appid + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", out_refund_no='" + out_refund_no + '\'' +
                ", refund_fee='" + refund_fee + '\'' +
                ", total_fee='" + total_fee + '\'' +
                ", transaction_id='" + transaction_id + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
