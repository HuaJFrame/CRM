package com.huajframe.xycrm.mapper;

import com.huajframe.xycrm.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Hua JFarmer
* @description 针对表【sys_menu】的数据库操作Mapper
* @createDate 2023-04-19 17:35:17
* @Entity com.huajframe.xycrm.entity.Menu
*/
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据角色id查权限列表
     * @param id
     * @return
     */
    List<Menu> selectMenuByRoleId(Long id);
}




