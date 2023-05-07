package com.huajframe.xycrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.entity.CustomerLoss;
import com.huajframe.xycrm.query.CusLossQuery;
import com.huajframe.xycrm.service.CustomerLossService;
import com.huajframe.xycrm.mapper.CustomerLossMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
* @author Hua JFarmer
* @description 针对表【sys_customer_loss】的数据库操作Service实现
* @createDate 2023-05-02 21:23:27
*/
@Service
public class CustomerLossServiceImpl extends ServiceImpl<CustomerLossMapper, CustomerLoss>
    implements CustomerLossService{


    /**
     * 分页条件按插件
     *
     * @param query
     * @return
     */
    @Override
    public PageUtils selectPageByParam(CusLossQuery query) {
        Page<CustomerLoss> pageParam = new Page<>(query.getPage(), query.getLimit());
        QueryWrapper<CustomerLoss> wrapper = new QueryWrapper<>();
        appendWrapper(wrapper, query);
        Page<CustomerLoss> page = baseMapper.selectPage(pageParam, wrapper);
        return new PageUtils(page);
    }

    /**
     * 拼接wrapper
     * @param wrapper
     * @param query
     */
    private void appendWrapper(QueryWrapper<CustomerLoss> wrapper, CusLossQuery query) {
        if(StringUtils.hasLength(query.getCusName())){
            wrapper.like("cus_name", query.getCusName());
        }
        if(StringUtils.hasLength(query.getCusNo())){
            wrapper.eq("cus_no", query.getCusNo());
        }
        if(!StringUtils.isEmpty(query.getState())){
            wrapper.eq("state", query.getState());
        }
    }
}




