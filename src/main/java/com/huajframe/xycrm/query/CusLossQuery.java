package com.huajframe.xycrm.query;

import com.huajframe.xycrm.common.util.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Hua JFarmer
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CusLossQuery extends BaseQuery {
    /**
     * 客户编号
     */
    private String cusNo;

    /**
     * 客户姓名
     */
    private String cusName;

    /**
     * 流失状态 0未流失 1流失
     */
    private Integer state;
}
