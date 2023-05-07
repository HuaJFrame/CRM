package com.huajframe.xycrm.service;

import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.entity.OrderDetails;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huajframe.xycrm.query.OrderItemQuery;

/**
* @author Hua JFarmer
* @description 针对表【sys_order_details】的数据库操作Service
* @createDate 2023-05-02 21:23:27
*/
public interface OrderDetailsService extends IService<OrderDetails> {

    /**
     * 分页查询
     * @param query
     * @return
     */
    PageUtils selectPageByParam(OrderItemQuery query);
}
