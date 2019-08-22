package com.kw13.message.entity;

    import com.kw13.common.BaseEntity;
    import java.time.LocalDateTime;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * wx用户服务消息关系表
    * </p>
*
* @author cq
* @since 2019-08-20
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public class TUserMsg extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer sysId;

            /**
            * 关联wx用户
            */
    private String refAccount;

            /**
            * 关联信息详情
            */
    private Integer refMsg;

            /**
            * 1消息未读/2已读
            */
    private Integer readed;

    private Boolean deleted;

    private LocalDateTime createAt;

            /**
            * 消息预览,消息头
            */
    private String msgTitle;

            /**
            * pay支付/case病历/general一般
            */
    private String msgType;

            /**
            * 消息内容
            */
    private String msgContent;


}
