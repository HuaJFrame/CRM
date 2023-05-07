package com.huajframe.xycrm.config.security;

import cn.hutool.json.JSONUtil;
import com.huajframe.xycrm.common.util.R;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 重写登录失败逻辑
 *
 * @author Hua JFarmer
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {
        // 响应登录失败结果
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream =
                httpServletResponse.getOutputStream();
        String message = e.getMessage();
        if (e instanceof BadCredentialsException) {
            message = "用户名或者密码错误！";
        }
        // 登录失败后返回失败
        outputStream.write(JSONUtil.toJsonStr(R.error(message)).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
