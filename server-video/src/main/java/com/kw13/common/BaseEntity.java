package com.kw13.common;


import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


/**
 * Created by cheng on 2019/8/12.
 */
@Data
@Accessors(chain = true)
public class BaseEntity {
    private String createBy;
    private String editBy;
    private LocalDateTime createAt;
    private LocalDateTime editAt;
}
