package com.huajframe.xycrm.mapper;

import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Hua JFarmer
* @description 针对表【sys_role】的数据库操作Mapper
* @createDate 2023-04-19 17:35:17
* @Entity com.huajframe.xycrm.entity.Role
*/
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id获取角色列表
     * @param id
     * @return
     */
    List<Role> selectRoleListByUserId(Long id);

    /**
     * 返回树形
     * @param id
     * @return
     */
    R menuTree(Long id);
}




