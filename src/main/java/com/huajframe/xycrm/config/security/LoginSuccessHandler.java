package com.huajframe.xycrm.config.security;

import cn.hutool.json.JSONUtil;
import com.huajframe.xycrm.common.util.JwtUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.common.vo.UserInfoVO;
import com.huajframe.xycrm.entity.Menu;
import com.huajframe.xycrm.entity.Role;
import com.huajframe.xycrm.entity.User;
import com.huajframe.xycrm.mapper.MenuMapper;
import com.huajframe.xycrm.mapper.RoleMapper;
import com.huajframe.xycrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author Hua JFarmer
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    /**
     * 重写登录成功后响应
     * @param httpServletRequest
     * @param httpServletResponse
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream =
                httpServletResponse.getOutputStream();
        //获取用户名
        String username= authentication.getName();
        //获取权限
        Set<String> permissions = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        String token = JwtUtils.genJwtToken(username);
        User user1 = userService.getUserByUsername(username);
        UserInfoVO userInfoVO = new UserInfoVO(user1.getId(), username, permissions);
        List<Menu> menuList = userService.getMenuByUsername(username);
        // 登录成功后返回前端jwttoken
        outputStream.write(
                JSONUtil.toJsonStr(R.ok("登录成功")
                                .put("authorization",token)
                                .put("menuList", menuList)
                                .put("userInfo", userInfoVO))
                        .getBytes(StandardCharsets.UTF_8)
        );
        outputStream.flush();
        outputStream.close();
    }
}
