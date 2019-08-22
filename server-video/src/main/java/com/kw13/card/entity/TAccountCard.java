package com.kw13.card.entity;

    import com.kw13.common.BaseEntity;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 用户卡关系表
    * </p>
*
* @author cq
* @since 2019-08-20
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public class TAccountCard extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer sysId;

            /**
            * 关联卡实例
            */
    private Integer refCardInstance;

    private Boolean deleted;
    private String refAccount;


}
