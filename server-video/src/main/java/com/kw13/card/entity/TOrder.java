package com.kw13.card.entity;

import com.kw13.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 购卡订单表
 * </p>
 *
 * @author cq
 * @since 2019-08-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TOrder extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * yyyyMMddhhmmss+account_id后四位
     */
    private String sysId;

    /**
     * 关联卡定义
     */
    private Integer refCard;

    /**
     * 订单金额,单位分
     */
    private Integer fee;

    /**
     * 实际支付金额 单位分
     */
    private Integer feePayed;

    /**
     * 支付类型:zfb支付宝/wx微信
     */
    private String payType;

    /**
     * 交易id
     */
    private String transactionId;

    /**
     * 交易状态:0待支付/1已支付/2支付失败
     */
    private Integer status;

    private Boolean deleted;
    /**
     * 购卡数量
     */
    private Integer buyQuantity;
    private String productName;
    private String refAccount;
}
