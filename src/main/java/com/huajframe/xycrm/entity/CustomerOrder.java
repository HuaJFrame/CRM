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
 * @TableName sys_customer_order
 */
@TableName(value ="sys_customer_order")
@Data
public class CustomerOrder implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 客户id
     */
    private Integer cusId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 下单时间
     */
    private Date orderDate;

    /**
     * 地址
     */
    private String address;

    /**
     * 订单状态
     */
    private Integer state;

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