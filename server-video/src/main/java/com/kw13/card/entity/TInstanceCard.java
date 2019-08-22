package com.kw13.card.entity;

    import com.kw13.common.BaseEntity;
    import java.time.LocalDateTime;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 存储购买或领取的卡实例
    * </p>
*
* @author cq
* @since 2019-08-20
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public class TInstanceCard extends BaseEntity {

    private static final long serialVersionUID = 1L;

            /**
            * 自增主键
            */
    private Integer sysId;

            /**
            * 制卡批次 yyyyMMddhhmmss+后台系统用户userid后4位
            */
    private String cardBatchId;

            /**
            * 关联卡定义
            */
    private Integer refCard;

            /**
            * 12位数字卡号,600开头,结尾8,中间8位不能包含4/7
            */
    private Integer cardNum;

            /**
            * 激活码:4位数字,非0开头,不包含4/7
            */
    private Integer activeCode;

            /**
            * 激活时间
            */
    private LocalDateTime activeTime;

            /**
            * 0待使用(可激活)/1已赠送/2已激活(已失效)/3超出激活期限(已失效)
            */
    private Integer status;

            /**
            * 有效期截止时间(包含)
            */
    private LocalDateTime expirationAt;

            /**
            * 卡类型 0电子/1实体
            */
    private Integer cardType;

            /**
            * 自关联字段,描述:派生卡的父卡.如:赠送卡场景中,受赠人的卡关联赠送人的卡
            */
    private Integer refSelfId;

    private Boolean deleted;


}
