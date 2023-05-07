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
public class CusDevPlanQuery extends BaseQuery {
    /**
     * 营销机会id
     */
    @NotNull(message = "营销机会id不能为空")
    private Long sid;
}
