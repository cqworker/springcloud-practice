package com.kw13.login.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kw13.account.entity.TKwAccount;
import com.kw13.account.service.ITKwAccountService;
import com.kw13.common.BaseResponse;
import com.kw13.common.ResponseGenerator;
import com.kw13.common.db.ChangeDB;
import com.kw13.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import com.kw13.common.BaseController;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author cq
 * @since 2019-08-21
 */
@Controller
@RequestMapping("/user")
public class LoginController extends BaseController {
    @Autowired
    private ITKwAccountService accountService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping("/login")
    @ChangeDB
    public BaseResponse login(@RequestBody TKwAccount account) {
        String loginName = account.getLoginName();
        String password = account.getPassword();
        if (loginName==null ||"".equals(loginName.trim())) {
            return ResponseGenerator.genFailResult("用户名为空", "000002");
        }
        if (loginName==null ||"".equals(loginName.trim())) {
            return ResponseGenerator.genFailResult("密码为空", "000002");
        }

        TKwAccount one = accountService.getOne(Wrappers.<TKwAccount>lambdaQuery()
                .eq(TKwAccount::getLoginName, loginName)
                .eq(TKwAccount::getPassword, password)
                .eq(TKwAccount::getDeleted, false)
        );
        if (one == null) {
            return ResponseGenerator.genFailResult("用户名或密码错误", "000001");
        }
        //发放token
        String tokeStr = jwtTokenUtil.getTokenStr(String.valueOf(one.getSysId()));

        return ResponseGenerator.genSuccessResult(tokeStr);
    }

    @RequestMapping("/whoami")
    public String whoami() {
        String currentUser = "";
        Object principl = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principl instanceof UserDetails) {
            currentUser = ((UserDetails) principl).getUsername();
        } else {
            currentUser = principl.toString();
        }
        return currentUser;
    }

}
