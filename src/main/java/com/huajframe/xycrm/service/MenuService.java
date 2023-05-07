package com.huajframe.xycrm.service;

import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Hua JFarmer
* @description 针对表【sys_menu】的数据库操作Service
* @createDate 2023-04-19 17:35:17
*/
public interface MenuService extends IService<Menu> {

    /**
     * 构建树形菜单导航栏
     * @param menuList 原始菜单列表
     * @return
     */
    List<Menu> buildTreeMenu(List<Menu> menuList);

    /**
     * 返回当前所有菜单
     * @return
     */
    R menuTree();

    /**
     * 添加菜单
     * @param menu
     * @return
     */
    int addMenu(Menu menu);

    /**
     * 更新菜单
     * @param menu
     * @return
     */
    int updateMenu(Menu menu);

    /**
     * 根据id删除
     * @param id
     */
    R deleteById(Long id);
}
