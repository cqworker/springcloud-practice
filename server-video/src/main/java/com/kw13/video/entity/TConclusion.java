package com.kw13.video.entity;

    import com.kw13.common.BaseEntity;
    import java.time.LocalDateTime;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 小结详情表
    * </p>
*
* @author cq
* @since 2019-08-20
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public class TConclusion extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer sysId;

            /**
            * 关联家庭成员
            */
    private String refMember;

            /**
            * 冗余家庭成员名
            */
    private String memberName;

            /**
            * 0.001 - 999.365描述范围,出生一天到百岁
            */
    private String memberAge;

            /**
            * m男/f女
            */
    private String memberGender;

            /**
            * 关联医生
            */
    private Integer refDoctor;

            /**
            * 医生姓名
            */
    private String doctorName;

    private LocalDateTime createAt;

            /**
            * 主诉
            */
    private String mainDes;

            /**
            * 初诊
            */
    private String diagnosis;

            /**
            * 咨询意见
            */
    private String opinion;

    private Boolean deleted;


}
