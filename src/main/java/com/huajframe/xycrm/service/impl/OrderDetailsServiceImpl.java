package com.huajframe.xycrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.entity.OrderDetails;
import com.huajframe.xycrm.query.OrderItemQuery;
import com.huajframe.xycrm.service.OrderDetailsService;
import com.huajframe.xycrm.mapper.OrderDetailsMapper;
import org.springframework.stereotype.Service;

/**
* @author Hua JFarmer
* @description 针对表【sys_order_details】的数据库操作Service实现
* @createDate 2023-05-02 21:23:27
*/
@Service
public class OrderDetailsServiceImpl extends ServiceImpl<OrderDetailsMapper, OrderDetails>
    implements OrderDetailsService{

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    @Override
    public PageUtils selectPageByParam(OrderItemQuery query) {
        Page<OrderDetails> pageParam = new Page<>(query.getPage(), query.getLimit());
        QueryWrapper<OrderDetails> wrapper = new QueryWrapper<>();
        if(query.getOrderId() != null){
            wrapper.eq("order_id", query.getOrderId());
        }
        Page<OrderDetails> page = baseMapper.selectPage(pageParam, wrapper);
        return new PageUtils(page);
    }
}




