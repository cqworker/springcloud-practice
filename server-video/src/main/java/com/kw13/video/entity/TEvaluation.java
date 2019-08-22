package com.kw13.video.entity;

    import com.kw13.common.BaseEntity;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 评价表
    * </p>
*
* @author cq
* @since 2019-08-20
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public class TEvaluation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer sysId;

            /**
            * 关联视频记录
            */
    private Integer refVedioRecord;

            /**
            * 关联评价用户
            */
    private String refAccount;

            /**
            * 关联问题
            */
    private Integer refQuestion;

            /**
            * y满意(不存在问题)/n不满意(存在问题)
            */
    private String question;

    private Integer refDoctor;

    private Boolean deleted;


}
