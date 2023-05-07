package com.huajframe.xycrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huajframe.xycrm.common.enums.CustomerServeEnums;
import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.entity.CustomerServe;
import com.huajframe.xycrm.query.CusServeQuery;
import com.huajframe.xycrm.service.CustomerServeService;
import com.huajframe.xycrm.mapper.CustomerServeMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
* @author Hua JFarmer
* @description 针对表【sys_customer_serve】的数据库操作Service实现
* @createDate 2023-05-02 21:23:27
*/
@Service
public class CustomerServeServiceImpl extends ServiceImpl<CustomerServeMapper, CustomerServe>
    implements CustomerServeService{

    /**
     * 分页条件查询
     *
     * @param query
     * @return
     */
    @Override
    public PageUtils selectPageByParam(CusServeQuery query) {
        Page<CustomerServe> pageParam = new Page<>(query.getPage(), query.getLimit());
        QueryWrapper<CustomerServe> wrapper = new QueryWrapper<>();
        appendWrapper(wrapper, query);
        Page<CustomerServe> page = baseMapper.selectPage(pageParam, wrapper);
        return new PageUtils(page);
    }

    /**
     * 添加服务
     *
     * @param customerServe
     * @return
     */
    @Override
    public int addCustomerServe(CustomerServe customerServe) {
        Date date = new Date();
        customerServe.setCreateDate(date);
        customerServe.setUpdateDate(date);
        return baseMapper.insert(customerServe);
    }

    /**
     * 修改服务
     *
     * @param customerServe
     * @return
     */
    @Override
    public int updateCustomerServe(CustomerServe customerServe) {
        String state = customerServe.getState();
        if(state.equals(CustomerServeEnums.ASSIGNED.getState())){
            Date date = new Date();
            customerServe.setAssignTime(date);
            customerServe.setUpdateDate(date);
        }else if(state.equals(CustomerServeEnums.PROCED.getState())){
            //服务处理
            Date date = new Date();
            customerServe.setServiceProceTime(date);
            customerServe.setUpdateDate(date);
        } else if(state.equals(CustomerServeEnums.FEED_BACK.getState())){
            //服务反馈
            customerServe.setUpdateDate(new Date());
            customerServe.setState(CustomerServeEnums.ARCHIVED.getState());
        }
        return baseMapper.updateById(customerServe);
    }

    /**
     * 客户反馈分析，根据myd进行分析
     * 这是返回柱状图的数据
     *
     * @return
     */
    @Override
    public R countByMyd() {
        List<Map<String,Object>> list = baseMapper.countByMyd();
        List<String> data1List=new ArrayList<>();
        List<Long> data2List=new ArrayList<>();
        list.forEach(m->{
            data1List.add((String) m.get("myd"));
            data2List.add((Long) m.get("num"));
        });
        return R.ok().put("data1",data1List).put("data2",data2List);
    }

    /**
     * 客户反馈分析，根据myd进行分析
     * 这是返回饼图的数据
     *
     * @return
     */
    @Override
    public R countByMyd2() {
        List<Map<String,Object>> list = baseMapper.countByMyd();
        List<Map<String, Object>> result = list.stream().map((m) -> {
            Map<String, Object> ret = new HashMap<>(2);
            ret.put("name", m.get("myd"));
            ret.put("value", m.get("num"));
            return ret;
        }).collect(Collectors.toList());
        return R.ok().put("data", result);
    }

    /**
     * 拼接wrapper
     * @param wrapper
     * @param query
     */
    private void appendWrapper(QueryWrapper<CustomerServe> wrapper, CusServeQuery query) {
        if(StringUtils.hasLength(query.getCustomer())){
            wrapper.like("customer", query.getCustomer());
        }
        if(!StringUtils.isEmpty(query.getServeType())){
            wrapper.eq("serve_type", query.getServeType());
        }
        if(!StringUtils.isEmpty(query.getState())){
            wrapper.eq("state", query.getState());
        }
        if(StringUtils.hasLength(query.getAssigner())){
            wrapper.eq("assigner", query.getAssigner());
        }
    }
}




