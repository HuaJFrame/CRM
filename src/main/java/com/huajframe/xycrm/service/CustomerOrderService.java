package com.huajframe.xycrm.service;

import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.entity.CustomerOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huajframe.xycrm.query.OrderQuery;

/**
* @author Hua JFarmer
* @description 针对表【sys_customer_order】的数据库操作Service
* @createDate 2023-05-02 21:23:27
*/
public interface CustomerOrderService extends IService<CustomerOrder> {

    /**
     * 分页查询订单
     * @param query
     * @return
     */
    PageUtils selectPageByParam(OrderQuery query);
}
