package com.kw13.common.exceptions;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 全局参数处理
 */
@ControllerAdvice
public class ParamControllerAdvice {

    @InitBinder
    protected void initBinder(WebDataBinder binder) {

        //日期参数处理日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


}
