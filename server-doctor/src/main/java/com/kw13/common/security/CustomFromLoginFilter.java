package com.kw13.common.security;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kw13.account.entity.TKwAccount;
import com.kw13.account.service.ITKwAccountService;
import com.kw13.login.entity.TKwRole;
import com.kw13.login.entity.TKwUserRole;
import com.kw13.login.service.ITKwRoleService;
import com.kw13.login.service.ITKwUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CustomFromLoginFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private ITKwUserRoleService kwUserRoleService;
    @Autowired
    private ITKwRoleService kwRoleService;
    @Autowired
    private ITKwAccountService kwAccountService;

    CustomFromLoginFilter(String defaultFilterProcessesUrl) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl, HttpMethod.POST.name()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");

        TKwAccount kwAccount = kwAccountService.getOne(Wrappers.<TKwAccount>lambdaQuery()
                .eq(TKwAccount::getLoginName, username)
                .eq(TKwAccount::getPassword, password)
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
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new UsernamePasswordAuthenticationToken(kwAccount.getLoginName(), kwAccount.getPassword(), simpleGrantedAuthorities);
    }

}
