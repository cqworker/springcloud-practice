package com.kw13.login.entity;

    import com.kw13.common.BaseEntity;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 角色用户关系表
    * </p>
*
* @author cq
* @since 2019-08-21
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public class TKwUserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer sysId;

    private Integer refRole;

    private Integer refAccount;

    private Boolean deleted;


}
