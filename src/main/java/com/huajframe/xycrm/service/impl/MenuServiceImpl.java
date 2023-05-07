package com.huajframe.xycrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.common.util.RedisUtil;
import com.huajframe.xycrm.entity.Menu;
import com.huajframe.xycrm.entity.RoleMenu;
import com.huajframe.xycrm.mapper.RoleMenuMapper;
import com.huajframe.xycrm.service.MenuService;
import com.huajframe.xycrm.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author Hua JFarmer
* @description 针对表【sys_menu】的数据库操作Service实现
* @createDate 2023-04-19 17:35:17
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    /**
     * 构建树形菜单导航栏
     *
     * @param menuList 原始菜单列表
     * @return
     */
    @Override
    public List<Menu> buildTreeMenu(List<Menu> menuList) {
        for(Menu menu : menuList){
            // 寻找子节点
            for(Menu e : menuList){
                if(e.getParentId().equals(menu.getId())){
                    if(menu.getChildren() == null){
                        menu.setChildren(new ArrayList<>());
                    }
                    menu.getChildren().add(e);
                }
            }
        }
        return menuList.stream().filter(menu -> menu.getParentId() == 0).collect(Collectors.toList());
    }

    /**
     * 返回当前所有菜单
     *
     * @return
     */
    @Override
    public R menuTree() {
        List<Menu> menuTree = (List<Menu>) redisUtil.get("menuTree");
        if(menuTree != null){
            return R.ok().put("menuTree", menuTree);
        }
        List<Menu> menuList = list(new QueryWrapper<Menu>()
                .orderByAsc("order_num"));
        List<Menu> menuList1 = buildTreeMenu(menuList);
        redisUtil.set("menuTree", menuList1, 20);
        return R.ok().put("menuTree", menuList1);
    }

    /**
     * 添加菜单
     *
     * @param menu
     * @return
     */
    @Override
    public int addMenu(Menu menu) {
        if(StringUtils.isEmpty(menu.getIcon())) {
            menu.setIcon("#");
        }
        Date date = new Date();
        menu.setCreateTime(date);
        menu.setUpdateTime(date);
        redisUtil.del("menuTree");
        return baseMapper.insert(menu);
    }

    /**
     * 更新菜单
     *
     * @param menu
     * @return
     */
    @Override
    public int updateMenu(Menu menu) {
        Date date = new Date();
        menu.setUpdateTime(date);
        redisUtil.del("menuTree");
        return baseMapper.updateById(menu);
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R deleteById(Long id) {
        // 判断id为不为空
        if(id == null || id == 0){
            return R.error("要删除的菜单id不能为空");
        }
        // 判断该菜单是否有子菜单
        int count = count(new QueryWrapper<Menu>().eq("parent_id", id));
        if(count > 0){
            return R.error("请先删除其子菜单");
        }
        // 删除该菜单
        baseMapper.deleteById(id);
        // 删除角色权限表该菜单的记录
        roleMenuMapper.delete(
                new UpdateWrapper<RoleMenu>()
                        .eq("menu_id", id)
        );
        // 删除redis缓存中数据
        redisUtil.del("menuTree");
        redisUtil.del("menu::" + id);
        return R.ok();
    }
}




