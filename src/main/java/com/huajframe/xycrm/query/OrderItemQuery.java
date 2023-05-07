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
public class OrderItemQuery extends BaseQuery {
    /**
     * 订单id
     */
    @NotNull(message = "订单id不能为空")
    private Long orderId;
}
