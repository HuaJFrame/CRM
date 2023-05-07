package com.huajframe.xycrm.query;

import com.huajframe.xycrm.common.util.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Hua JFarmer
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerQuery extends BaseQuery {
    /**
     * 客户编号
     */
    private String khno;

    /**
     * 客户名称
     */
    private String name;

    /**
     * 级别
     */
    private String level;
}
