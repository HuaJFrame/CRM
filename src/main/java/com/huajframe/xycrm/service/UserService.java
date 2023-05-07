package com.huajframe.xycrm.service;

import com.huajframe.xycrm.common.po.AssignRolePO;
import com.huajframe.xycrm.common.po.PasswordPO;
import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.entity.Menu;
import com.huajframe.xycrm.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huajframe.xycrm.query.UserQuery;

import java.util.List;

/**
* @author Hua JFarmer
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2023-04-19 17:35:17
*/
public interface UserService extends IService<User> {

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 根据用户id查询权限
     * @param id
     * @return
     */
    String getUserAuthorityInfo(Long id);

    /**
     * 查出用户所具有的菜单权限
     * @param username
     * @return
     */
    List<Menu> getMenuByUsername(String username);

    /**
     * 修改密码
     * @param passwordPO
     * @return
     */
    R modifyPassword(PasswordPO passwordPO);

    /**
     * 分页条件查询
     * @param userQuery
     * @return
     */
    PageUtils selectPageByParam(UserQuery userQuery);

    /**
     * 添加用户
     * @param user
     */
    int addUser(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 更新用户状态
     * @param userId
     * @param status
     * @return
     */
    R updateUserStatusByUserId(Long userId, Integer status);

    /**
     * 重置密码
     * @param id
     * @return
     */
    R resetPwd(Long id);

    /**
     * 用于分配角色时展示，返回时包含用户拥有角色和未拥有角色的标识
     * @param id 用户id
     * @return
     */
    R listUserAllRole(Long id);

    /**
     * 给用户分配角色
     * @param assignRolePO
     * @return
     */
    R assignRole(AssignRolePO assignRolePO);

    /**
     * 删除用户
     * @param asList
     * @return
     */
    R removeUserByIds(List<Long> asList);

    /**
     * 查询所有销售人员
     * @return
     */
    R selectUserByRoleName(String role);
}
