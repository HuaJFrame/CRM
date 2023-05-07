package com.huajframe.xycrm.common.util;

import lombok.Data;

/**
 * 基础查询信息
 * @author Hua JFarmer
 */
@Data
public class BaseQuery {
    private Integer page; // 第几页
    private Integer limit; // 每页记录数
}
