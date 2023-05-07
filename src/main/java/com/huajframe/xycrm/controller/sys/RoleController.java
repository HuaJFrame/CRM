package com.huajframe.xycrm.controller.sys;

import com.huajframe.xycrm.common.po.AssignMenuPO;
import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.common.util.RedisUtil;
import com.huajframe.xycrm.entity.Role;
import com.huajframe.xycrm.query.RoleQuery;
import com.huajframe.xycrm.service.MenuService;
import com.huajframe.xycrm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * @author Hua JFarmer
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private MenuService menuService;
    /**
     * 列表
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('system:role:list')")
    public R list(@RequestBody RoleQuery roleQuery) {
        PageUtils page = roleService.selectPageByParam(roleQuery);
        return R.ok().put("page", page);
    }

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('system:role:query')")
    public R findById(@PathVariable(value = "id")Long id){
        Role role1 = (Role) redisUtil.get("role::" + id);
        if(role1 != null){
            return R.ok().put("role", role1);
        }
        Role role = roleService.getById(id);
        redisUtil.set("role::" + id, role, 20);
        return R.ok().put("role", role);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('system:role:add')" + "||" + "hasAuthority('system:role:edit')")
    public R save(@RequestBody @Valid Role role) {
        int result;
        if (role.getId() == null || role.getId() == 0) {
            result = roleService.addRole(role);
        } else {
            result = roleService.updateRole(role);
        }
        if(result == 1){
            return R.ok();
        }else{
            return R.error("操作失败");
        }
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('system:role:delete')")
    public R delete(Long[] ids){
        return roleService.removeRoleByIds(Arrays.asList(ids));
    }

    /**
     * 返回当前所有菜单
     * @return
     */
    @GetMapping("/menuTree")
    @PreAuthorize("hasAuthority('system:role:menu')")
    public R menuTree(){
        return menuService.menuTree();
    }

    /**
     * 获取当前角色拥有的权限
     * @param id
     * @return
     */
    @GetMapping("/menus/{id}")
    @PreAuthorize("hasAuthority('system:role:menu')")
    public R menusByRoleId(@PathVariable(value = "id")Long id){
        return roleService.menusByRoleId(id);
    }

    /**
     * 分配权限
     * @param
     * @return
     */
    @PostMapping("/assignMenu")
    @PreAuthorize("hasAuthority('system:role:menu')")
    public R assignMenu(@RequestBody AssignMenuPO assignMenuPO){
        return roleService.assignMenu(assignMenuPO);
    }

}
