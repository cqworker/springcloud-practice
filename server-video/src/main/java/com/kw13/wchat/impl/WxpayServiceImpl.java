package com.kw13.wchat.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.kw13.account.entity.TWxAccount;
import com.kw13.account.service.ITWxAccountService;
import com.kw13.card.entity.TAccountCard;
import com.kw13.card.entity.TInstanceCard;
import com.kw13.card.entity.TOrder;
import com.kw13.card.service.ITAccountCardService;
import com.kw13.card.service.ITCardDefindService;
import com.kw13.card.service.ITInstanceCardService;
import com.kw13.card.service.ITOrderService;
import com.kw13.common.constant.CardType;
import com.kw13.common.constant.OrderStatus;
import com.kw13.message.entity.TUserMsg;
import com.kw13.message.service.ITUserMsgService;
import com.kw13.util.*;
import com.kw13.wchat.WxpayService;
import com.kw13.wchat.pay.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.time.LocalDateTime;
import java.util.Objects;


/**
 * Created by cheng on 2019/5/21.
 */
@Service
public class WxpayServiceImpl implements WxpayService {

    private final Logger log = LogManager.getLogger(getClass());
    /**
     * 请求固定报文格式
     */
    private final String JS_API = "JSAPI";
    private final String MD5 = "MD5";
    private final String SUCCESS_RESULT = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    private final String FAIL_RESULT = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml>";

//    @Autowired
//    private RedisService redisService;
    @Autowired
    private Environment env;
    @Autowired
    private ITOrderService orderService;
    @Autowired
    private ITInstanceCardService inscardService;
    @Autowired
    private ITAccountCardService accountCardService;
    @Autowired
    private ITWxAccountService userService;
    @Autowired
    private HttpUtil httpUtil;
    @Autowired
    private ITUserMsgService userMsgService;
    @Autowired
    private ITCardDefindService cardDefindService;


    @Value("${wechat.api.pay-unifiedorder}")
    private String payUnifiedOrderUrl;
    @Value("${wechat.account.appid}")
    private String appId;
    @Value("${wechat.account.appsecret}")
    private String appSecret;
    @Value("${wechat.account.mchid}")
    private String mchId;
    @Value("${wechat.account.mchKey}")
    private String mchKey;

    @Value("${wechat.config.notify-url}")
    private String notifyUrl;


    @Override
    public WechatSign toWechatPay(WechatOrderPay info) throws Exception {
        //获取用户openid
        TWxAccount user = userService.getOne(Wrappers.<TWxAccount>lambdaQuery()
                .eq(TWxAccount::getSysId, LoginInfoUtil.getLoginInfo().getUserSysid()));

        String nonceStr = IDGen.getRandomStr(6);

        WechatPayXml xml = new WechatPayXml();
        xml.setBody(info.getProductName());
        //随机字符串
        xml.setNonce_str(nonceStr);
        //用户标识
        xml.setOpenid(user.getOpenid());
        //商户订单号
        xml.setOut_trade_no(info.getOrderSysId());
        //终端IP
        xml.setSpbill_create_ip(LoginInfoUtil.getLoginInfo().getIp());
        //订单总金额，单位为分
        xml.setTotal_fee(String.valueOf(info.getFee()));
        //#微信分配的小程序ID
        xml.setAppid(appId);
        //#微信支付分配的商户号
        xml.setMch_id(mchId);
        //回调地址
        xml.setNotify_url(notifyUrl);
        //交易类型小程序取值如下：JSAPI 参考:https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_2
        xml.setTrade_type(JS_API);
        log.info("构造微信支付报文xml:  {}", xml);
        try {
            //微信返回的签名值,以商户key加密  参考:https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_3
            xml.setSign(SignUtils.encodeOrderSign(xml, mchKey));
        } catch (Exception e) {
            log.info("构造微信签名出错: {}", e);
            return null;
        }

        String retStr = null;
        try {
            retStr = httpUtil.postForString(payUnifiedOrderUrl, XmlUtils.getXmlForObject(xml));
        } catch (Exception e) {
            log.info("去微信统一下单出错: {}", e);
            return null;
        }
        log.info("订单号{},调用微信支付返回结果：{}", info.getOrderSysId(), retStr);
        return createWechatSign(retStr, nonceStr);

    }

    /**
     * 支付成功后 执行回调方法
     *
     * @param xml
     * @return
     */
    @Override
    public String payNotify(WechatNotifyInDto xml) {

        try {
            log.info("支付成功微信回传结果：{}", xml.toString());
            //验签
            String sign = SignUtils.encodeOrderSign(xml, mchKey);
            if (!sign.equals(xml.getSign())) {
                log.warn("签名结果异常");
                return FAIL_RESULT;
            }
            //根据OrderId查询支付订单中是否存在
            TOrder order = orderService.getById(xml.getOut_trade_no());

            // 找不到对应的订单
            if (Objects.isNull(order.getSysId())) {
                log.warn("未查询到对应订单");
                return FAIL_RESULT;
            }

            Integer refCardefind = order.getRefCard();

            // 通知幂等
            if (OrderStatus.YI_ZHIFU == (order.getStatus())) {
                log.warn("已完成订单号 {} 支付状态同步+1", xml.getOut_trade_no());
            } else {
                //校验金额
                boolean res = false;
                if (!"prod".equals(env.getProperty("spring.profiles.active"))) {
                    res = true;
                    log.info("非生产环境不进行金额校验");
                }
                log.info("订单: {}应付金额: {},实付金额: {}", order.getSysId(), order.getFee(), xml.getCash_fee());
                res = ((int) order.getFee() == (int) (xml.getCash_fee()));
                //购卡
                if (res) {
                    //支付成功 更新支付单状态和   实际支付金额
                    order.setStatus(OrderStatus.YI_ZHIFU);
                    order.setFeePayed(xml.getCash_fee());
                    order.setEditAt(LocalDateTime.now());
                    boolean b = orderService.updateById(order);
                    log.info("订单{} 支付成功,更新支付单状态:{}", order.getSysId(), b);

                    //一次下单购买的卡数量
                    int quantity = Integer.valueOf(order.getBuyQuantity());
                    log.info("订单{}购买了{}张卡", order.getSysId(), quantity);
                    int count = 0;
                    for (int i = 0; i < quantity; i++) {
                        count++;
                        //卡实例中增加卡
                        TInstanceCard card = new TInstanceCard();
                        card.setCardNum(Integer.valueOf(IDGen.getCardNum(8)));
                        card.setCardType(CardType.ELECTRONIC);
//                        card.setExpirationAt();
                        card.setRefCard(order.getRefCard());
                        card.setCreateAt(LocalDateTime.now());
                        boolean save1 = inscardService.save(card);
                        if (save1) {
                            Integer sysId = card.getSysId();
                            //为用户发放卡
                            TAccountCard uc = new TAccountCard();
                            uc.setRefCardInstance(sysId);
                            uc.setRefAccount(IDGen.getCardNum(8));
                            boolean save = accountCardService.save(uc);
                            if (!save) {
                                //todo 发起退款 目前由客服操作
                                log.info("UserBuyCard 表中的 sysId:{}订单,第{}张卡未加入到用户卡包,发起退款", order.getSysId(), i + 1);
                            } else {
                                log.info("UserBuyCard 表中的 sysId:{}订单,第{}张卡加入到用户卡包成功", order.getSysId(), i + 1);
                            }
                        }
                    }
                    //购买成功后新增一条未读消息
                    StringBuilder msgStr = new StringBuilder();
                    msgStr.append(count);
                    msgStr.append("张");
                    msgStr.append(order.getProductName());
                    //激活截止时间
                    msgStr.append(",");
                    msgStr.append(env.getProperty("card-active-date"));
                    TUserMsg msg = new TUserMsg();
                    msg.setRefAccount(order.getRefAccount());
                    msg.setCreateAt(LocalDateTime.now());
                    boolean save1 = userMsgService.save(msg);
                    log.info("购买成功后推送成功消息:{}", save1);
                } else {
                    //更新实际支付金额和支付订单状态
                    order.setStatus(OrderStatus.ZHIFU_FAIL);
                    boolean b2 = orderService.updateById(order);
                    log.info("{} 支付失败", b2, order.getSysId());
                }

            }
            return SUCCESS_RESULT;
        } catch (Exception e) {
            log.error("微信支付回调异常 e:{}", e);
            return FAIL_RESULT;
        }
    }

    @Override
    public boolean refundWxPay(WechatRefund refund) throws Exception {
        boolean flag = false;
        refund.setAppid(appId);
        refund.setMch_id(mchId);
        refund.setNonce_str(IDGen.getRandomStr(15));
        refund.setSign(SignUtils.encodeOrderSign(refund, mchKey));//微信返回的签名值,以商户key加密
        log.info("退款xml报文:{}", XmlUtils.getXmlForObject(refund));
        InputStream instream = null;
        KeyStore keyStore = null;
        SSLContext sslcontext = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient httpclient = null;
        ClassPathResource resource = null;
        try {
            keyStore = KeyStore.getInstance("PKCS12");
            resource = new ClassPathResource(env.getProperty("wechat.account.cert"));
            instream = resource.getInputStream();
            keyStore.load(instream, env.getProperty("wechat.account.mchid").toCharArray());
            instream.close();
            sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, env.getProperty("wechat.account.mchid").toCharArray()).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null,

                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            HttpPost httpost = new HttpPost(env.getProperty("wechat.config.refund-url")); // 退款url
            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            String xml = XmlUtils.getXmlForObject(refund);
            httpost.setEntity(new StringEntity(xml, "UTF-8"));
            response = httpclient.execute(httpost);
            HttpEntity entity = response.getEntity();
            String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            log.info("微信退款返回{}", jsonStr);
            EntityUtils.consume(entity);
            if (jsonStr.contains("<![CDATA[SUCCESS]]>") && jsonStr.contains("<![CDATA[OK]]>")) {
                flag = true;
            }
        } catch (Exception e) {
            log.info("退款出错{}", e);
            flag = false;
        } finally {
            if (instream != null) {
                try {
                    instream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                response.close();
            }
            if (httpclient != null) {
                httpclient.close();
            }
        }
        return flag;
    }

    /**
     * 返回json , 前端可直接用于唤起支付组件
     */
    private WechatSign createWechatSign(String retStr, String nonceStr) {
        WechatSign wechatSign = new WechatSign();
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode node = null;
        try {
            node = xmlMapper.readTree(retStr);
        } catch (IOException e) {
            log.info("xml -> json 解析出错: {}", e);
            return null;
        }
        wechatSign.setAppId(appId);
        wechatSign.setTimeStamp(String.valueOf(System.currentTimeMillis()));
        wechatSign.setNonceStr(nonceStr);
        wechatSign.setPackageStr("prepay_id=" + node.get("prepay_id").asText());
        wechatSign.setSignType(MD5);
        String paySign = null;
        //生成 验签
        try {
            paySign = SignUtils.encodeOrderSign(wechatSign, mchKey);
        } catch (Exception e) {
            log.info("生成签名出错: {}", e);
            return null;
        }
        wechatSign.setSign(paySign);
        // appId参与签名生成 但是不返回
        wechatSign.setAppId(null);
        return wechatSign;
    }
}
