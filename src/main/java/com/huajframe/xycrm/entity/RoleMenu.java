package com.huajframe.xycrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName sys_role_menu
 */
@TableName(value ="sys_role_menu")
@Data
@NoArgsConstructor
public class RoleMenu implements Serializable {
    /**
     * 角色菜单主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public RoleMenu(Long roleId, Long id) {
        this.roleId = roleId;
        this.menuId = id;
    }
}