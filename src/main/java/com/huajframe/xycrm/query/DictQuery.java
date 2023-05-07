package com.huajframe.xycrm.query;

import com.huajframe.xycrm.common.util.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Hua JFarmer
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DictQuery extends BaseQuery {
    /**
     * 字典名
     */
    private String dataDicName;

    /**
     * 字典值
     */
    private String dataDicValue;
}
