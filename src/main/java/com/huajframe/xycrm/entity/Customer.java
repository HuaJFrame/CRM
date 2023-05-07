package com.huajframe.xycrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 
 * @TableName sys_customer
 */
@TableName(value ="sys_customer")
@Data
public class Customer implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 客户编号
     */
    private String khno;

    /**
     * 客户名称
     */
    @NotBlank(message = "客户名称不能为空")
    private String name;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空")
    private String area;

    /**
     * 客户经理
     */
    @NotBlank(message = "客户经理不能为空")
    private String cusManager;

    /**
     * 级别
     */
    @NotBlank(message = "客户级别不能为空")
    private String level;

    /**
     * 满意度
     */
    @NotBlank(message = "满意度不能为空")
    private String myd;

    /**
     * 客户信用度
     */
    @NotBlank(message = "客户信用度不能为空")
    private String xyd;

    /**
     * 详细地址
     */
    @NotBlank(message = "详细地址不能为空")
    private String address;

    /**
     * 邮编
     */
    @NotBlank(message = "邮编不能为空")
    private String postCode;

    /**
     * 联系电话
     */
    @Pattern(regexp = "^1(3[0-9]|4[57]|5[0-35-9]|7[0678]|8[0-9])\\d{8}$", message = "请规范电话号码输入")
    private String phone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 网址
     */
    private String webSite;

    /**
     * 营业执照注册号
     */
    private String yyzzzch;

    /**
     * 法人代表
     */
    private String fr;

    /**
     * 注册资金
     */
    private String zczj;

    /**
     * 年营业额
     */
    private String nyye;

    /**
     * 开户银行
     */
    @NotBlank(message = "开户银行不能为空")
    private String khyh;

    /**
     * 开户账号
     */
    @NotBlank(message = "开户账号不能为空")
    private String khzh;

    /**
     * 地税登记号	
     */
    private String dsdjh;

    /**
     * 国税登记号
     */
    private String gsdjh;

    /**
     * 流失状态 0未流失 1暂缓流失
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