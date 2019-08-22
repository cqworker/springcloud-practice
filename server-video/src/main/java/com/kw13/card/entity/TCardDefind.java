package com.kw13.card.entity;

    import com.kw13.common.BaseEntity;
    import com.baomidou.mybatisplus.annotation.TableName;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 可售卖的卡的基本信息表
    * </p>
*
* @author cq
* @since 2019-08-20
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @TableName("t_card_defind")
    public class TCardDefind extends BaseEntity {

    private static final long serialVersionUID = 1L;

            /**
            * 自增主键
            */
    private Integer sysId;

            /**
            * 卡名称
            */
    private String cardName;

            /**
            * 是否可赠送,默认可赠送
            */
    private Boolean canSend;

            /**
            * 售价(范围:0.01 - 9999.99)
            */
    private String price;

            /**
            * 卡包含的服务时长:单位天
            */
    private Integer timeLength;

            /**
            * 是否已逻辑删除
            */
    private Boolean deleted;


}
