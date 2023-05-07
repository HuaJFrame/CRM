package com.huajframe.xycrm.config;

import com.huajframe.xycrm.config.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Hua JFarmer
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private JwtLogoutSuccessHandler jwtLogoutSuccessHandler;
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    private static final String[] URL_WHITELIST = {
            "/login",
            "/logout",
            "/captcha",
            "/password",
            "/image/**",
            "/test/**"
    };

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager());
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启跨域 和 csrf攻击 关闭
        http
            .cors()
            .and()
            .csrf()
            .disable()
        // 登录配置
        .formLogin()
            .successHandler(loginSuccessHandler)
            .failureHandler(loginFailureHandler)
            // .successHandler()
            // .failureHandler()
            // .and()
            // .logout()
            // .logoutSuccessHandler()
        // 退出登录配置
        .and()
            .logout()
            .logoutSuccessHandler(jwtLogoutSuccessHandler)

        // session禁用配置
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        // 拦截规则配置
        .and()
            .authorizeRequests()
             // 这些全部许可
            .antMatchers(URL_WHITELIST).permitAll()
            // 其余请求全部需要认证
            .anyRequest().authenticated()

        // 权限不够返回配置
        .and()
            .exceptionHandling()
            .accessDeniedHandler(myAccessDeniedHandler)

        // 异常处理器配置
        .and()
            .exceptionHandling()
        // 自定义过滤器配置
        .and()
            .addFilter(jwtAuthenticationFilter());
    }
}
