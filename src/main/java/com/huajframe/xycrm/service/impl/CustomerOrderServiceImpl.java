package com.huajframe.xycrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.entity.Customer;
import com.huajframe.xycrm.entity.CustomerOrder;
import com.huajframe.xycrm.query.OrderQuery;
import com.huajframe.xycrm.service.CustomerOrderService;
import com.huajframe.xycrm.mapper.CustomerOrderMapper;
import org.springframework.stereotype.Service;

/**
* @author Hua JFarmer
* @description 针对表【sys_customer_order】的数据库操作Service实现
* @createDate 2023-05-02 21:23:27
*/
@Service
public class CustomerOrderServiceImpl extends ServiceImpl<CustomerOrderMapper, CustomerOrder>
    implements CustomerOrderService{

    /**
     * 分页查询订单
     *
     * @param query
     * @return
     */
    @Override
    public PageUtils selectPageByParam(OrderQuery query) {
        Page<CustomerOrder> pageParam = new Page<>(query.getPage(), query.getLimit());
        QueryWrapper<CustomerOrder> wrapper = new QueryWrapper<>();
        if(query.getCusId() != null){
            wrapper.eq("cus_id", query.getCusId());
        }
        Page<CustomerOrder> page = baseMapper.selectPage(pageParam, wrapper);
        return new PageUtils(page);
    }
}




