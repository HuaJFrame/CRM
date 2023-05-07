package com.huajframe.xycrm.service;

import com.huajframe.xycrm.common.po.AssignMenuPO;
import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huajframe.xycrm.query.RoleQuery;

import java.util.List;

/**
* @author Hua JFarmer
* @description 针对表【sys_role】的数据库操作Service
* @createDate 2023-04-19 17:35:17
*/
public interface RoleService extends IService<Role> {

    PageUtils selectPageByParam(RoleQuery roleQuery);

    /**
     * 添加角色
     * @param role
     * @return
     */
    int addRole(Role role);

    /**
     * 更新角色
     * @param role
     * @return
     */
    int updateRole(Role role);

    /**
     * 删除角色
     * @param ids
     * @return
     */
    R removeRoleByIds(List<Long> ids);

    /**
     * 获取当前角色拥有的权限id列表
     * @param id
     * @return
     */
    R menusByRoleId(Long id);

    /**
     * 分配权限
     * @param assignMenuPO
     * @return
     */
    R assignMenu(AssignMenuPO assignMenuPO);
}
