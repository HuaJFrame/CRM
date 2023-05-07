package com.huajframe.xycrm.query;

import com.huajframe.xycrm.common.util.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Hua JFarmer
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CusServeQuery extends BaseQuery {
    /**
     * 服务类型
     */
    private String serveType;
    /**
     * 客户
     */
    private String customer;

    /**
     * 服务状态
     */
    private String state;

    /**
     * 服务分配人
     */
    private String assigner;
}
