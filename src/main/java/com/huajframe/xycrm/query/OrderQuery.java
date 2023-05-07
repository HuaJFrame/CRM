package com.huajframe.xycrm.query;

import com.huajframe.xycrm.common.util.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author Hua JFarmer
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderQuery extends BaseQuery {
    /**
     * 客户id
     */
    @NotNull(message = "客户id不能为空")
    private Integer cusId;
}
