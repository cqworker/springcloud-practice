package com.kw13.account.entity;

    import com.kw13.common.BaseEntity;
    import java.time.LocalDate;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * kw平台用户表
    * </p>
*
* @author cq
* @since 2019-08-21
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public class TKwAccount extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer sysId;

            /**
            * 登录名
            */
    private String loginName;

            /**
            * passwordMD5
            */
    private String password;

            /**
            * 真实姓名
            */
    private String realName;

    private Integer phone;

            /**
            * m男/f女
            */
    private String gender;

    private LocalDate birthday;

    private Boolean deleted;


}
