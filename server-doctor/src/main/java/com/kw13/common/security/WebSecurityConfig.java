//package com.kw13.common.security;
//
//import com.kw13.account.service.ITKwAccountService;
//import com.kw13.common.db.ChangeDB;
//import com.kw13.login.service.ITKwRoleService;
//import com.kw13.login.service.ITKwUserRoleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private ITKwUserRoleService kwUserRoleService;
//    @Autowired
//    private ITKwRoleService kwRoleService;
//    @Autowired
//    private ITKwAccountService kwAccountService;
//
//    /**
//     * 2.
//     * 匹配 "/" 路径，不需要权限即可访问
//     * 匹配 "/user" 及其以下所有路径，都需要 "USER" 权限
//     * 退出登录的地址为 "/logout"，退出成功后跳转到页面 "/login"
//     * 默认启用 CSRF
//     */
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/", "/home/*").permitAll()
//                .antMatchers("/account/**").hasAuthority("ADMIN")
//                .antMatchers("/case/**").hasAuthority("DOCTOR")
//                .antMatchers("/callback/**").hasAuthority("ASSISTANT")
//                .antMatchers("/statistics/**").hasAuthority("STATISTICS")
//                .and()
//                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
//        http.addFilterAt(customFromLoginFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//
//    /**
//     * 自定义认证过滤器
//     * 1.过滤 url 匹配 post的/user/login 进行权限获取缓存
//     */
//    private CustomFromLoginFilter customFromLoginFilter() {
//        return new CustomFromLoginFilter("/user/login",kwUserRoleService,kwRoleService,kwAccountService);
//    }
//
//}
