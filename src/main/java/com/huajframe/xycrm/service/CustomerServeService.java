package com.huajframe.xycrm.service;

import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.entity.CustomerServe;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huajframe.xycrm.query.CusServeQuery;


/**
 * @author Hua JFarmer
 * @description 针对表【sys_customer_serve】的数据库操作Service
 * @createDate 2023-05-02 21:23:27
 */
public interface CustomerServeService extends IService<CustomerServe> {

    /**
     * 分页条件查询
     *
     * @param query
     * @return
     */
    PageUtils selectPageByParam(CusServeQuery query);

    /**
     * 添加服务
     *
     * @param customerServe
     * @return
     */
    int addCustomerServe(CustomerServe customerServe);

    /**
     * 修改服务
     *
     * @param customerServe
     * @return
     */
    int updateCustomerServe(CustomerServe customerServe);

    /**
     * 客户反馈分析，根据myd进行分析
     * 这是返回柱状图的数据
     *
     * @return
     */
    R countByMyd();

    /**
     * 客户反馈分析，根据myd进行分析
     * 这是返回饼图的数据
     *
     * @return
     */
    R countByMyd2();
}
