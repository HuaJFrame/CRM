package com.huajframe.xycrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Hua JFarmer
 * @TableName sys_customer_serve
 */
@TableName(value ="sys_customer_serve")
@Data
public class CustomerServe implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 服务类型
     */
    @NotNull(message = "服务类型不能为空")
    private String serveType;

    /**
     * 概要
     */
    @NotBlank(message = "服务概要不能为空")
    private String overview;

    /**
     * 客户
     */
    @NotBlank(message = "客户不能为空")
    private String customer;

    /**
     * 服务状态
     */
    @NotBlank(message = "服务状态不能为空")
    private String state;

    /**
     * 服务请求
     */
    @NotBlank(message = "服务内容不能为空")
    private String serviceRequest;

    /**
     * 服务创建人
     */
    @NotBlank(message = "创建人不能为空")
    private String createPeople;

    /**
     * 服务分配人
     */
    private String assigner;

    /**
     * 分配时间
     */
    private Date assignTime;

    /**
     * 服务处理
     */
    private String serviceProce;

    /**
     * 服务处理人
     */
    private String serviceProcePeople;

    /**
     * 服务处理时间
     */
    private Date serviceProceTime;

    /**
     * 处理结果
     */
    private String serviceProceResult;

    /**
     * 满意度
     */
    private String myd;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 创建时间
     */
    private Date createDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}