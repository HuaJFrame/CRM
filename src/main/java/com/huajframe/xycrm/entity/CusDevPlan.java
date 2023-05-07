package com.huajframe.xycrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Hua JFarmer
 * @TableName sys_cus_dev_plan
 */
@TableName(value ="sys_cus_dev_plan")
@Data
public class CusDevPlan implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 营销机会id
     */
    @NotNull(message = "营销机会id不能为空")
    private Integer saleChanceId;

    /**
     * 计划内容
     */
    @NotBlank(message = "计划内容不能为空")
    private String planItem;

    /**
     * 计划日期
     */
    @NotNull(message = "计划日期不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date planDate;

    /**
     * 执行效果
     */
    private String exeAffect;

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