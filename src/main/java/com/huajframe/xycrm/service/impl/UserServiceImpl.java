package com.huajframe.xycrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huajframe.xycrm.common.po.AssignRolePO;
import com.huajframe.xycrm.common.po.PasswordPO;
import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.common.util.RedisUtil;
import com.huajframe.xycrm.common.vo.RoleVO;
import com.huajframe.xycrm.entity.Menu;
import com.huajframe.xycrm.entity.Role;
import com.huajframe.xycrm.entity.User;
import com.huajframe.xycrm.entity.UserRole;
import com.huajframe.xycrm.mapper.MenuMapper;
import com.huajframe.xycrm.mapper.RoleMapper;
import com.huajframe.xycrm.mapper.UserRoleMapper;
import com.huajframe.xycrm.query.UserQuery;
import com.huajframe.xycrm.service.MenuService;
import com.huajframe.xycrm.service.UserService;
import com.huajframe.xycrm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
* @author Hua JFarmer
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2023-04-19 17:35:17
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private MenuService menuService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    @Override
    public User getUserByUsername(String username) {
        User user = (User) redisUtil.get("user::" + username);
        if(user == null){
            User user1 = getOne(new QueryWrapper<User>().eq("username", username));
            redisUtil.set("user::" + username, user1, 30);
            return user1;
        }
        return user;
    }

    /**
     * 根据用户id查询权限
     *
     * @param id
     * @return 权限字符串
     */
    @Override
    public String getUserAuthorityInfo(Long id) {
        String authorityInfo = (String) redisUtil.get("userAuthorityInfo::" + id);
        if(!StringUtils.isEmpty(authorityInfo)){
            return authorityInfo;
        }

        StringBuilder authority=new StringBuilder();
        // 根据用户id获取所有的角色
        List<Role> roleList = roleMapper.selectRoleListByUserId(id);
        // 按照特定的格式拼接
        if(!roleList.isEmpty()){
            String roleCodeStr = roleList.stream().map(r -> "ROLE_" +
                    r.getCode()).collect(Collectors.joining(","));
            authority.append(roleCodeStr);
        }
        // 遍历角色，获取所有菜单权限
        Set<String> menuCodeSet = new HashSet<>();
        for(Role role : roleList){
            // 根据角色获取对应的菜单
            List<Menu> menuList = menuMapper.selectMenuByRoleId(role.getId());
            for(Menu menu: menuList){
                String perms = menu.getPerms();
                if(!StringUtils.isEmpty(perms)){
                    menuCodeSet.add(perms);
                }
            }
        }
        if(!menuCodeSet.isEmpty()){
            authority.append(",");
            String menuCodeStr = String.join(",", menuCodeSet);
            authority.append(menuCodeStr);
        }
        String authorityStr = authority.toString();
        redisUtil.set("userAuthorityInfo::" + id, authorityStr, 30);
        return authorityStr;
    }


    @Override
    public List<Menu> getMenuByUsername(String username){
        List<Menu> menuList1 = (List<Menu>) redisUtil.get("userMenuList::" + username);
        if(menuList1 != null){
            return menuList1;
        }
        // 获取用户
        User user = getUserByUsername(username);
        // 获取用户角色
        List<Role> roles = roleMapper.selectRoleListByUserId(user.getId());
        // 根据角色获取权限信息
        // 遍历角色，获取所有菜单权限
        Set<Menu> menuSet=new HashSet<>();
        for(Role role : roles){
            List<Menu> sysMenuList = menuMapper.selectMenuByRoleId(role.getId());
            menuSet.addAll(sysMenuList);
        }

        List<Menu> menuList = new ArrayList<>(menuSet);
        // 排序
        menuList.sort(Comparator.comparing(Menu::getOrderNum));
        // 返回好构建好的树形菜单
        List<Menu> menuList2 = menuService.buildTreeMenu(menuList);
        redisUtil.set("userMenuList::" + username, menuList2, 60);
        return menuList2;
    }

    /**
     * 修改密码
     *
     * @param passwordPO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R modifyPassword(PasswordPO passwordPO) {
        if(!passwordPO.getNewPassword().equals(passwordPO.getConfirmPassword())){
            return R.error("两次密码输入不一致");
        }

        User user = getById(passwordPO.getUserId());
        if(bCryptPasswordEncoder.matches(passwordPO.getOldPassword(), user.getPassword())){
            redisUtil.del("user::" + passwordPO.getUsername());
            redisUtil.del("userMenuList::" + passwordPO.getUsername());
            user.setPassword(bCryptPasswordEncoder.encode(passwordPO.getNewPassword()));
            user.setUpdateTime(new Date());
            updateById(user);
        }else{
            return R.error("旧密码错误！");
        }
        return R.ok();
    }

    /**
     * 分页条件查询
     *
     * @param userQuery
     * @return
     */
    @Override
    public PageUtils selectPageByParam(UserQuery userQuery) {
        Page<User> pageParam = new Page<>(userQuery.getPage(), userQuery.getLimit());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        appendWrapper(wrapper, userQuery);
        Page<User> userPage = userMapper.selectPage(pageParam, wrapper);
        List<User> users = userPage.getRecords();
        for (User user : users) {
            List<Role> roles = roleMapper.selectRoleListByUserId(user.getId());
            user.setRoleList(roles);
        }
        return new PageUtils(userPage);
    }

    /**
     * 添加用户
     *
     * @param user
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addUser(User user) {
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        return userMapper.insert(user);
    }



    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(User user) {
        Date date = new Date();
        user.setUpdateTime(date);
        redisUtil.del("user::" + user.getUsername());
        redisUtil.del("user::" + user.getUsername());
        return userMapper.updateById(user);
    }

    /**
     * 更新用户状态
     *
     * @param userId
     * @param status
     * @return
     */
    @Override
    public R updateUserStatusByUserId(Long userId, Integer status) {
        update(new UpdateWrapper<User>()
                        .set("status", status)
                        .eq("id", userId));
        return R.ok();
    }

    /**
     * 重置密码
     *
     * @param id
     * @return
     */
    @Override
    public R resetPwd(Long id) {
        update(
                new UpdateWrapper<User>()
                        .set("password", bCryptPasswordEncoder.encode("123456"))
                        .eq("id", id)
        );
        return R.ok();
    }

    /**
     * 用于分配角色时展示，返回时包含用户拥有角色和未拥有角色的标识
     *
     * @param id 用户id
     * @return
     */
    @Override
    public R listUserAllRole(Long id) {
        List<Role> roleList = (List<Role>) redisUtil.get("userAllRoles::" + id);
        if(roleList != null){
            return R.ok().put("roleList", roleList);
        }
        List<RoleVO> roleVOS = userMapper.listUserAllRole(id);
        redisUtil.set("userAllRoles::" + id, roleVOS, 20);
        return R.ok().put("roleList", roleVOS);
    }

    /**
     * 给用户分配角色
     *
     * @param assignRolePO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public R assignRole(AssignRolePO assignRolePO) {
        Long userId = assignRolePO.getId();
        //删除用户原本的角色
        userRoleMapper.delete(
                new UpdateWrapper<UserRole>()
                        .eq("user_id", userId)
        );
        redisUtil.del("userAllRoles::" + assignRolePO.getId());
        //插入新角色
        List<Long> roleIds = assignRolePO.getRoles();
        if(roleIds != null){
            roleIds.forEach((roleId) -> {
                userRoleMapper.insert(new UserRole(userId, roleId));
            });
        }
        return R.ok();
    }

    /**
     * 删除用户
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R removeUserByIds(List<Long> ids) {
        removeByIds(ids);
        userRoleMapper.delete(
                new UpdateWrapper<UserRole>()
                        .in("user_id", ids)
        );
        for (Long id : ids) {
            redisUtil.del("userAllRoles::" + id);
            redisUtil.del("userAuthorityInfo::" + id);
        }
        return R.ok();
    }

    /**
     * 查询所有销售人员
     *
     * @return
     */
    @Override
    public R selectUserByRoleName(String role) {
        List<User> users = (List<User>) redisUtil.get("UserByRole::" + role);
        if(users != null){
            return R.ok().put("userRoles", users);
        }
        List<User> sales = baseMapper.selectUserByRoleName(role);
        redisUtil.set("UserByRole::" + role, sales, 10);
        return R.ok().put("userRoles", sales);
    }

    /**
     * 条件查询拼接sql
     * @param wrapper
     * @param userQuery
     */
    private void appendWrapper(QueryWrapper<User> wrapper, UserQuery userQuery){
        if(StringUtils.hasLength(userQuery.getUsername())){
            wrapper.like("username", userQuery.getUsername());
        }
        if(StringUtils.hasLength(userQuery.getPhoneNumber())){
            wrapper.eq("phone_number", userQuery.getPhoneNumber());
        }
        if(!StringUtils.isEmpty(userQuery.getStatus())){
            wrapper.eq("status", userQuery.getStatus());
        }
    }
}




