package com.kw13.account.entity;

import com.kw13.common.BaseEntity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 登录C端的微信用户账号表
 * </p>
 *
 * @author cq
 * @since 2019-08-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TWxAccount extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * yyyyMMddHHmmss+openId后4位
     */
    private String sysId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像链接,受限url长度不超过4K
     */
    private String imageUrl;

    private String openid;

    private String unionid;

    /**
     * 是否会存储+86?
     */
    private Integer phone;

    /**
     * 国家
     */
    private String country;

    /**
     * 省
     */
    private String privince;

    /**
     * 市
     */
    private String city;

    private LocalDateTime lastLoginTime;

    /**
     * y是/n否
     */
    private String delete;


}
