package com.kw13.common.security;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.gson.Gson;
import com.kw13.account.entity.TKwAccount;
import com.kw13.account.service.ITKwAccountService;
import com.kw13.login.entity.TKwRole;
import com.kw13.login.entity.TKwUserRole;
import com.kw13.login.service.ITKwRoleService;
import com.kw13.login.service.ITKwUserRoleService;
import com.kw13.util.GetRequestJsonUtil;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * 自定义表单登录
 */
public class CustomJSONLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final ITKwUserRoleService kwUserRoleService;
    private final ITKwRoleService kwRoleService;
    private final ITKwAccountService kwAccountService;

    CustomJSONLoginFilter(String defaultFilterProcessesUrl, ITKwUserRoleService kwUserRoleService, ITKwRoleService kwRoleService, ITKwAccountService kwAccountService) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl, HttpMethod.POST.name()));
        this.kwUserRoleService = kwUserRoleService;
        this.kwRoleService = kwRoleService;
        this.kwAccountService = kwAccountService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String requestPostStr = GetRequestJsonUtil.getRequestPostStr(httpServletRequest);
        Gson gson = new Gson();
        //查询是否存在用户
        TKwAccount tKwAccount = gson.fromJson(requestPostStr, TKwAccount.class);

        TKwAccount kwAccount = kwAccountService.getOne(Wrappers.<TKwAccount>lambdaQuery()
                .eq(TKwAccount::getLoginName, tKwAccount.getLoginName())
                .eq(TKwAccount::getPassword, tKwAccount.getPassword())
                .eq(TKwAccount::getDeleted, false)
        );
        if (kwAccount == null) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        //获取用户的角色
        List<TKwUserRole> roleList = kwUserRoleService.list(Wrappers.<TKwUserRole>lambdaQuery()
                .eq(TKwUserRole::getRefAccount, kwAccount.getSysId())
                .eq(TKwUserRole::getDeleted, false)
        );
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (TKwUserRole ur : roleList) {
            TKwRole role = kwRoleService.getOne(Wrappers.<TKwRole>lambdaQuery()
                    .eq(TKwRole::getSysId, ur.getRefRole())
                    .eq(TKwRole::getDeleted, false)
            );
            // user :roles
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        //将userid放图request中在成功的回调方法中生成token
        httpServletRequest.setAttribute("userId",kwAccount.getSysId());
        return new UsernamePasswordAuthenticationToken(kwAccount.getLoginName(), kwAccount.getPassword(), simpleGrantedAuthorities);
    }

}
