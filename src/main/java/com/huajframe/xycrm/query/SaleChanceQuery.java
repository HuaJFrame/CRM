package com.huajframe.xycrm.query;

import com.huajframe.xycrm.common.util.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Hua JFarmer
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SaleChanceQuery extends BaseQuery {
    //客户名称
    private String customerName;

    //分配状态
    private Integer state;

    //分配人
    private String assignMan;

    //开发状态
    private Integer devResult;
}
