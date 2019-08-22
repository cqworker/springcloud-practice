package com.kw13.wchat;


import com.kw13.wchat.pay.WechatNotifyInDto;
import com.kw13.wchat.pay.WechatOrderPay;
import com.kw13.wchat.pay.WechatRefund;
import com.kw13.wchat.pay.WechatSign;

/**
 * 微信支付相关
 * Created by cheng on 2019/5/21.
 */
public interface WxpayService {

    /**
     * 统一下单
     * @param inDto
     * @return
     */
    WechatSign toWechatPay(WechatOrderPay inDto) throws Exception;

    /**
     * 支付结果回调
     * @param inDto
     * @return
     */
    String payNotify(WechatNotifyInDto inDto);

    /**
     * 退款
     * @param refund
     * @return
     */
    boolean refundWxPay(WechatRefund refund) throws Exception;
}
