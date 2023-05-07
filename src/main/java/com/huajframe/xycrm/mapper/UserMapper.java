package com.huajframe.xycrm.mapper;

import com.huajframe.xycrm.common.vo.RoleVO;
import com.huajframe.xycrm.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Hua JFarmer
* @description 针对表【sys_user】的数据库操作Mapper
* @createDate 2023-04-19 17:35:17
* @Entity com.huajframe.xycrm.entity.User
*/
public interface UserMapper extends BaseMapper<User> {

    /**
     * 用于分配角色时展示，返回时包含用户拥有角色和未拥有角色的标识
     *
     * @param id 用户id
     * @return
     */
    List<RoleVO> listUserAllRole(Long id);

    /**
     * 查询所有销售
     * @return
     */
    List<User> selectUserByRoleName(String role);
}




