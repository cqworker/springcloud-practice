package com.kw13.family.entity;

    import com.kw13.common.BaseEntity;
    import java.time.LocalDate;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 用户家庭成员表
    * </p>
*
* @author cq
* @since 2019-08-20
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public class TFamily extends BaseEntity {

    private static final long serialVersionUID = 1L;

            /**
            * 成员id 规则?
            */
    private String memberId;

            /**
            * 关联主账号id
            */
    private String refPrimaryAccount;

            /**
            * 0本人/
            */
    private Integer relationship;

            /**
            * 家庭成员姓名
            */
    private String realName;

            /**
            * 生日
            */
    private LocalDate birthday;

            /**
            * 性别:m男/f女
            */
    private String gender;

    private Boolean deleted;


}
