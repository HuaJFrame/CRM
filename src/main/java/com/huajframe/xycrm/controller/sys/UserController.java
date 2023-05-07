package com.huajframe.xycrm.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huajframe.xycrm.common.po.AssignRolePO;
import com.huajframe.xycrm.common.po.PasswordPO;
import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.common.util.RedisUtil;
import com.huajframe.xycrm.entity.User;
import com.huajframe.xycrm.query.UserQuery;
import com.huajframe.xycrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * 用户操作
 *
 * @author Hua JFarmer
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 修改密码
     *
     * @param
     * @return
     */
    @PostMapping("/updateUserPwd")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public R updateUserPwd(@RequestBody @Valid PasswordPO passwordPO) {
        return userService.modifyPassword(passwordPO);
    }

    /**
     * 列表
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('system:user:list')")
    public R list(@RequestBody UserQuery userQuery) {
        PageUtils page = userService.selectPageByParam(userQuery);
        return R.ok().put("page", page);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('system:user:add')" + "||" + "hasAuthority('system:user:edit')")
    public R save(@RequestBody @Valid User user) {
        int result;
        if (user.getId() == null || user.getId() == 0) {
            result = userService.addUser(user);
        } else {
            result = userService.updateUser(user);
        }
        if(result == 1){
            return R.ok();
        }else{
            return R.error("操作失败");
        }
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('system:user:query')")
    public R findById(@PathVariable(value = "id")Long id){
        User user = userService.getById(id);
        return R.ok().put("user", user);
    }

    /**
     * 添加时验证用户名是否重复
     * @param user
     * @return
     */
    @PostMapping("/checkUserName")
    @PreAuthorize("hasAuthority('system:user:query')")
    public R checkUserName(@RequestBody User user){
        if(userService.getUserByUsername(user.getUsername()) == null){
            return R.ok();
        }else{
            return R.error();
        }
    }

    /**
     * 更新用户状态
     * @param userId
     * @param status
     * @return
     */
    @GetMapping("/{id}/{status}")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public R updateUserStatus(@PathVariable("id") Long userId,
                              @PathVariable("status") Integer status){
        return userService.updateUserStatusByUserId(userId, status);
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('system:user:delete')")
    public R delete(Long[] ids){
        return userService.removeUserByIds(Arrays.asList(ids));
    }

    /**
     * 重置密码
     * @param id
     * @return
     */
    @GetMapping("/resetPwd/{id}")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public R resetPwd(@PathVariable("id") Long id){
         return userService.resetPwd(id);
    }

    /**
     * 用于分配角色时展示，返回时包含用户拥有角色和未拥有角色的标识
     * @param id 用户id
     * @return
     */
    @GetMapping("/listUserAllRole/{id}")
    @PreAuthorize("hasAuthority('system:user:role')")
    public R listUserAllRole(@PathVariable("id") Long id){
        return userService.listUserAllRole(id);
    }


    /**
     * 给用户分配角色
     * @param assignRolePO
     * @return
     */
    @PostMapping("/assignRole")
    @PreAuthorize("hasAuthority('system:user:role')")
    public R assignRole(@RequestBody @Valid AssignRolePO assignRolePO){
        return userService.assignRole(assignRolePO);
    }


    /**
     * 查询所有销售人员
     * @return
     */
    @GetMapping("/role")
    public R allSale(@RequestParam("role") String role){
        return userService.selectUserByRoleName(role);
    }
}
