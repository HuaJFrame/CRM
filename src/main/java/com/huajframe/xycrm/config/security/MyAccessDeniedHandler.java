package com.huajframe.xycrm.config.security;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import com.huajframe.xycrm.common.util.JwtUtils;
import com.huajframe.xycrm.common.util.R;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 权限不够执行返回
 * @author Hua JFarmer
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        outputStream.write(
                JSONUtil.toJsonStr(R.error(HttpStatus.HTTP_UNAUTHORIZED,"权限不够，禁止访问！"))
                        .getBytes(StandardCharsets.UTF_8)
        );
        outputStream.flush();
        outputStream.close();
    }
}
