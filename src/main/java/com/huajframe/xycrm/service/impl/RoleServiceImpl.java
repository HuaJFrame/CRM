package com.huajframe.xycrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huajframe.xycrm.common.po.AssignMenuPO;
import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.common.util.RedisUtil;
import com.huajframe.xycrm.entity.Menu;
import com.huajframe.xycrm.entity.Role;
import com.huajframe.xycrm.entity.RoleMenu;
import com.huajframe.xycrm.entity.UserRole;
import com.huajframe.xycrm.mapper.RoleMenuMapper;
import com.huajframe.xycrm.mapper.UserRoleMapper;
import com.huajframe.xycrm.query.RoleQuery;
import com.huajframe.xycrm.service.MenuService;
import com.huajframe.xycrm.service.RoleMenuService;
import com.huajframe.xycrm.service.RoleService;
import com.huajframe.xycrm.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author Hua JFarmer
* @description 针对表【sys_role】的数据库操作Service实现
* @createDate 2023-04-19 17:35:17
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 条件查询角色
     * @param roleQuery
     * @return
     */
    @Override
    public PageUtils selectPageByParam(RoleQuery roleQuery) {
        Page<Role> pageParam = new Page<>(roleQuery.getPage(), roleQuery.getLimit());
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if(StringUtils.hasLength(roleQuery.getName())){
            wrapper.like("name", roleQuery.getName());
        }
        Page<Role> page = roleMapper.selectPage(pageParam, wrapper);
        return new PageUtils(page);
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @Override
    public int addRole(Role role) {
        Date date = new Date();
        role.setCreateTime(date);
        role.setUpdateTime(date);
        return roleMapper.insert(role);
    }

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRole(Role role) {
        Date date = new Date();
        role.setUpdateTime(date);
        redisUtil.del("role::" + role.getId());
        return roleMapper.updateById(role);
    }

    /**
     * 删除角色
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R removeRoleByIds(List<Long> ids) {
        removeByIds(ids);
        //删除用户角色表的数据
        userRoleMapper.delete(
                new UpdateWrapper<UserRole>()
                        .in("role_id", ids)
        );
        //删除用户角色表数据
        userRoleMapper.delete(
                new UpdateWrapper<UserRole>()
                        .in("role_id", ids)
        );
        //删除角色权限表的数据
        roleMenuMapper.delete(
                new UpdateWrapper<RoleMenu>()
                        .in("role_id", ids)
        );
        for (Long id : ids) {
            redisUtil.del("role::" + id);
            redisUtil.del("menusByRole::" + id);
        }
        return R.ok();
    }

    /**
     * 获取当前角色拥有的权限
     * @param id
     * @return
     */
    @Override
    public R menusByRoleId(Long id) {
        List<RoleMenu> menuRole = (List<RoleMenu>) redisUtil.get("menusByRole::" + id);
        if(menuRole != null){
            return R.ok().put("menuIdList", menuRole);
        }
        List<RoleMenu> roleMenuList = roleMenuMapper.selectList(
                new QueryWrapper<RoleMenu>().eq("role_id", id)
        );
        List<Long> menuIdList = roleMenuList.stream().map(RoleMenu::getMenuId)
                .collect(Collectors.toList());
        redisUtil.set("menusByRole::" + id, menuIdList, 20);
        return R.ok().put("menuIdList",menuIdList);
    }

    /**
     * 分配权限
     *
     * @param assignMenuPO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R assignMenu(AssignMenuPO assignMenuPO) {
        Long roleId = assignMenuPO.getRoleId();
        //删除以前的权限信息
        roleMenuMapper.delete(
                new UpdateWrapper<RoleMenu>()
                        .eq("role_id", roleId)
        );
        redisUtil.del("menusByRole::" + roleId);
        //插入新的权限
        List<Long> ids = assignMenuPO.getIds();
        if(ids != null){
            for (Long id : ids) {
                roleMenuMapper.insert(new RoleMenu(roleId, id));
            }
        }
        return R.ok();
    }
}




