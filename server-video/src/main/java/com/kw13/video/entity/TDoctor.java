package com.kw13.video.entity;


import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 医生表
 * </p>
 *
 * @author cq
 * @since 2019-08-21
 */
@Data
@Accessors(chain = true)
public class TDoctor {

    private static final long serialVersionUID = 1L;

    private Integer sysId;

    /**
     * 医生名
     */
    private String doctorName;

    /**
     * 医院
     */
    private String hospital;

    /**
     * 医生描述
     */
    private String des;

    private Integer phone;

    private String email;

    /**
     * m男/f女
     */
    private String gender;

    /**
     * (医生,公众号..)二维码
     */
    private String qrcode;

    private LocalDateTime createAt;

    private LocalDateTime editAt;

    private Integer createBy;

    private Integer editBy;

    /**
     * 0空闲/1暂时挂起
     */
    private Integer status;


}
