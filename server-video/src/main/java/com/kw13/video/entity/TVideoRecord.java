package com.kw13.video.entity;

    import com.kw13.common.BaseEntity;
    import com.baomidou.mybatisplus.annotation.TableName;
    import java.time.LocalDateTime;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 视频记录表
    * </p>
*
* @author cq
* @since 2019-08-20
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @TableName("t_video_record")
    public class TVideoRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer sysId;

            /**
            * 关联用户
            */
    private String refAccout;

            /**
            * 关联医生
            */
    private Integer refDoctor;

            /**
            * 用户点击发起视频时间
            */
    private LocalDateTime startAt;

            /**
            * 接通时间
            */
    private LocalDateTime connAt;

            /**
            * 结束时间
            */
    private LocalDateTime endAt;

            /**
            * 视频挂断事件:nomal正常/net网络问题/
            */
    private String endEvent;

            /**
            * 关联小结详情
            */
    private Integer refConclusion;

    private Boolean deleted;


}
