package com.huajframe.xycrm.config.security;

import com.huajframe.xycrm.entity.User;
import com.huajframe.xycrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hua JFarmer
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("用户名或者密码错误！");
        }
        //设置用户权限
        user.setAuthorities(getUserAuthority(user.getId()));
        return user;
    }

    /**
     * 根据用户id获取到用户权限
     * @param id
     * @return
     */
    public List<GrantedAuthority> getUserAuthority(Long id) {
        /*
        ROLE_admin,ROLE_common,system:user:resetPwd,system:role:delete,system:user:list,
                system:menu:query,system:menu:list,system:menu:add,system:user:delete,system:role:list,
                system:role:menu,system:user:edit,system:user:query,system:role:edit,system:user:add,
                system:user:role,system:menu:delete,system:role:add,system:role:query,system:menu:edit
        */
        String authority = userService.getUserAuthorityInfo(id);
        // System.out.println("authority="+authority);

        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
    }
}
