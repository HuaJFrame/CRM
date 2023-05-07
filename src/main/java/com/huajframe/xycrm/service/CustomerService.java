package com.huajframe.xycrm.service;

import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huajframe.xycrm.query.ContributionQuery;
import com.huajframe.xycrm.query.CustomerQuery;

import java.util.List;

/**
* @author Hua JFarmer
* @description 针对表【sys_customer】的数据库操作Service
* @createDate 2023-05-02 21:23:27
*/
public interface CustomerService extends IService<Customer> {

    /**
     * 定时检测流失客户
     */
    void updateCustomerState();

    /**
     * 分页条件查询
     * @param query
     * @return
     */
    PageUtils selectPageByParam(CustomerQuery query);

    /**
     * 添加客户
     * @param customer
     * @return
     */
    int addCustomer(Customer customer);

    /**
     * 更新客户
     * @param customer
     * @return
     */
    int updateCustomer(Customer customer);

    /**
     * 删除客户
     * @param ids
     * @return
     */
    R removeUserByIds(List<Long> ids);

    /**
     * 查询所有客户
     * @return
     */
    R selectCustomerList();

    /**
     * 折线图数据
     * @return
     */
    R countCustomerMake1();

    /**
     * 饼图数据
     * @return
     */
    R countCustomerMake2();

    /**
     * 分页条件查询客户贡献
     * @param query
     * @return
     */
    PageUtils selectContributionPageByParam(ContributionQuery query);
}
