package com.huajframe.xycrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * 
 * @TableName sys_sale_chance
 */
@TableName(value ="sys_sale_chance")
@Data
public class SaleChance implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 机会来源
     */
    @NotBlank(message = "机会来源不能为空")
    private String chanceSource;

    /**
     * 客户名称
     */
    @NotBlank(message = "客户名称不能为空")
    private String customerName;

    /**
     * 成功几率
     */
    @NotNull(message = "成功几率不能为空")
    @Max(100)
    @Min(1)
    private Integer cgjl;

    /**
     * 概要
     */
    private String overview;

    /**
     * 联系人
     */
    @NotBlank(message = "联系人不能为空")
    private String linkMan;

    /**
     * 手机号
     */
    @Pattern(regexp = "^1(3[0-9]|4[57]|5[0-35-9]|7[0678]|8[0-9])\\d{8}$", message = "请规范电话号码输入")
    private String linkPhone;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建人
     */
    @NotBlank(message = "创建人不能为空")
    private String createMan;


    /**
     * 分配人
     */
    private String assignMan;

    /**
     * 分配时间
     */
    private Date assignTime;

    /**
     * 分配状态
     */
    private Integer state;

    /**
     * 开发结果
     */
    private Integer devResult;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}