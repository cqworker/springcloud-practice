package com.kw13.account.controller;


import com.kw13.account.entity.TDoctor;
import com.kw13.common.BaseResponse;
import com.kw13.common.ResponseGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.kw13.common.BaseController;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * kw平台用户表 前端控制器
 * </p>
 *
 * @author cq
 * @since 2019-08-21
 */
@Controller
@RequestMapping("/account")
public class TKwAccountController extends BaseController {

    @PostMapping("/")
    public BaseResponse add(@RequestBody TDoctor doctor) {
        log.info("service-doctor 已启动");

        return ResponseGenerator.genSuccessResult(null);
    }
}
