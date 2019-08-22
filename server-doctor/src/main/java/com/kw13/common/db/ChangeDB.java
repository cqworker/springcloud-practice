package com.kw13.common.db;

import java.lang.annotation.*;

/**
 * Created by cheng on 2019/8/12.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ChangeDB {
    String value() default "datasource1";

}
