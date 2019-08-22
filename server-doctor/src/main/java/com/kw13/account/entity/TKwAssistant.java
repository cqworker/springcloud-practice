package com.kw13.account.entity;

    import com.kw13.common.BaseEntity;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 医助详细信息
    * </p>
*
* @author cq
* @since 2019-08-21
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public class TKwAssistant extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer sysId;

    private String realName;

    private String idCard;

    private Integer phone;

    private Boolean deleted;


}
