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
 * @author Hua JFarmer
 * @TableName sys_user_role
 */
@TableName(value ="sys_user_role")
@Data
@NoArgsConstructor
public class UserRole implements Serializable {
    /**
     * 用户角色主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public UserRole(Long userId, Long roleId){
        this.userId = userId;
        this.roleId = roleId;
    }
}