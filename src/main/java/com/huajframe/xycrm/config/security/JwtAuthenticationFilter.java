package com.huajframe.xycrm.config.security;

import cn.hutool.json.JSONUtil;
import com.huajframe.xycrm.common.util.CheckResult;
import com.huajframe.xycrm.common.util.JwtUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.entity.User;
import com.huajframe.xycrm.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * jwt自定义认证过滤器
 * @author Hua JFarmer
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private static final String[] URL_WHITELIST = {
            "/login",
            "/logout",
            "/captcha",
            "/password",
            "/image/**"
    };

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailServiceImpl userDetailService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("token");
        // System.out.println("请求url:" + request.getRequestURI());
        // 如果token是空或者url在白名单里 则放行 让后面的springsecurity认证过滤器去认证
        if (StringUtils.isEmpty(token) || Arrays.asList(URL_WHITELIST).contains(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }
        // 获取检查结果
        CheckResult checkResult = JwtUtils.validateJWT(token);
        if (!checkResult.isSuccess()) {
            response.setContentType("application/json;charset=UTF-8");
            ServletOutputStream outputStream =
                    response.getOutputStream();
            outputStream.write(JSONUtil.toJsonStr(R.error(401401,
                    "认证失败，请登录！")).getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
            return;
        }
        // 获取jwt校验结果
        Claims claims = JwtUtils.parseJWT(token);
        String username = claims.getSubject();
        User user = userService.getUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, userDetailService.getUserAuthority(user.getId()));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        chain.doFilter(request, response);
    }
}
