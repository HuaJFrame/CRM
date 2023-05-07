package com.huajframe.xycrm.service;

import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.entity.CustomerLoss;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huajframe.xycrm.query.CusLossQuery;

/**
* @author Hua JFarmer
* @description 针对表【sys_customer_loss】的数据库操作Service
* @createDate 2023-05-02 21:23:27
*/
public interface CustomerLossService extends IService<CustomerLoss> {


    /**
     * 分页条件按插件
     * @param query
     * @return
     */
    PageUtils selectPageByParam(CusLossQuery query);
}
