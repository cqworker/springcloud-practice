package com.kw13.wchat.pay;

/**
 * @Desc 用户微信订单表
 * @Author cq
 * @Date 2019/3/14
 */
public class WechatOrderPay {

    /** 购买费用(分) */
    private Long fee;
    /** 购买订单id */
    private String orderSysId;
    /** 购买产品名称  用户支付时显示*/
    private String productName;

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public String getOrderSysId() {
        return orderSysId;
    }

    public void setOrderSysId(String orderSysId) {
        this.orderSysId = orderSysId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
