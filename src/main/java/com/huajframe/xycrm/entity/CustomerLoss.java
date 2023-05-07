package com.huajframe.xycrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName sys_customer_loss
 */
@TableName(value ="sys_customer_loss")
@Data
public class CustomerLoss implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 客户编号
     */
    private String cusNo;

    /**
     * 客户姓名
     */
    private String cusName;

    /**
     * 客户经理
     */
    private String cusManager;

    /**
     * 最后下单时间
     */
    private Date lastOrderTime;

    /**
     * 确认流失时间
     */
    private Date confirmLossTime;

    /**
     * 流失状态 0未流失 1流失
     */
    private Integer state;

    /**
     * 流失原因
     */
    private String lossReason;

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