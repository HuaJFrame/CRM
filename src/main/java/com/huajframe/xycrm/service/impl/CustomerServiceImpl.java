package com.huajframe.xycrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huajframe.xycrm.common.enums.CusLossStateEnums;
import com.huajframe.xycrm.common.enums.CustomerStateEnums;
import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.common.util.RedisUtil;
import com.huajframe.xycrm.common.vo.ContributionVO;
import com.huajframe.xycrm.entity.Customer;
import com.huajframe.xycrm.entity.CustomerLoss;
import com.huajframe.xycrm.entity.CustomerOrder;
import com.huajframe.xycrm.mapper.CustomerOrderMapper;
import com.huajframe.xycrm.query.ContributionQuery;
import com.huajframe.xycrm.query.CustomerQuery;
import com.huajframe.xycrm.service.CustomerLossService;
import com.huajframe.xycrm.service.CustomerService;
import com.huajframe.xycrm.mapper.CustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
* @author Hua JFarmer
* @description 针对表【sys_customer】的数据库操作Service实现
* @createDate 2023-05-02 21:23:27
*/
@Service
@Slf4j
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer>
    implements CustomerService{

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerOrderMapper customerOrderMapper;
    @Autowired
    private CustomerLossService customerLossService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 定时检测流失客户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCustomerState() {
        //查询出流失客户数据
        //流失客户数据：
        //      1.创建时间大于6个月
        //      2.最新的订单时间是是6个月前创建的
        List<Customer> lossCustomers = customerMapper.queryLossCustomer();
        if (lossCustomers == null || lossCustomers.isEmpty()) {
            return;
        }
        //流失客户的id
        List<Long> lossCusIds = new ArrayList<>();
        //流失客户信息集合
        List<CustomerLoss> customerLosses = lossCustomers.stream().map(customer -> {
            CustomerLoss customerLoss = new CustomerLoss();
            //设置最后下单时间
            //  查询最后下单时间
            CustomerOrder lastCustomerOrder = customerOrderMapper.selectOne(
                    new QueryWrapper<CustomerOrder>()
                            .eq("cus_id", customer.getId())
                            .orderByDesc("order_date")
                            .last("limit 1")
            );
            if (lastCustomerOrder != null) {
                customerLoss.setLastOrderTime(lastCustomerOrder.getOrderDate());
            }
            //复制一些基本信息
            customerLoss.setCusManager(customer.getCusManager());
            customerLoss.setCusName(customer.getName());
            customerLoss.setCusNo(customer.getKhno());
            // 设置客户流失状态为暂缓流失状态
            customerLoss.setState(CusLossStateEnums.UN_LOSS.getCode());
            Date date = new Date();
            customerLoss.setCreateDate(date);
            customerLoss.setUpdateDate(date);

            lossCusIds.add(customer.getId());
            return customerLoss;
        }).collect(Collectors.toList());
        //更新数据库 customerLoss和customer表
        boolean result = customerLossService.saveBatch(customerLosses);
        if(!result){
            log.error("定时查询流失客户插入客户流失暂缓表失败！");
        }
        boolean result2 = update(
                new UpdateWrapper<Customer>()
                        .set("state", CustomerStateEnums.SURE_LOSS.getCode())
                        .in("id", lossCusIds)
        );
        if(!result2){
            log.error("定时查询流失客户修改客户表客户状态失败！");
        }
    }

    /**
     * 分页条件查询
     *
     * @param query
     * @return
     */
    @Override
    public PageUtils selectPageByParam(CustomerQuery query) {
        Page<Customer> pageParam = new Page<>(query.getPage(), query.getLimit());
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        appendWrapper(wrapper, query);
        Page<Customer> page = baseMapper.selectPage(pageParam, wrapper);
        return new PageUtils(page);
    }

    /**
     * 添加客户
     *
     * @param customer
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addCustomer(Customer customer) {
        customer.setState(0);
        Date date = new Date();
        customer.setCreateDate(date);
        customer.setUpdateDate(date);
        String khno = "KH_" + new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        customer.setKhno(khno);
        return baseMapper.insert(customer);
    }

    /**
     * 更新客户
     *
     * @param customer
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateCustomer(Customer customer) {
        customer.setUpdateDate(new Date());
        redisUtil.del("customer::" + customer.getId());
        return baseMapper.updateById(customer);
    }

    /**
     * 删除客户
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R removeUserByIds(List<Long> ids) {
        removeByIds(ids);
        for (Long id : ids) {
            redisUtil.del("customer::" + id);
        }
        return R.ok();
    }

    /**
     * 查询所有客户
     *
     * @return
     */
    @Override
    public R selectCustomerList() {
        List<String> customerList1 = (List<String>) redisUtil.get("customerList");
        if(customerList1 != null){
            return R.ok().put("customerList", customerList1);
        }
        List<String> customerList = baseMapper.selectCustomerList();
        redisUtil.set("customerList", customerList, 10);
        return R.ok().put("customerList", customerList);
    }

    /**
     * 折线图数据
     *
     * @return
     */
    @Override
    public R countCustomerMake1() {
        List<Map<String,Object>> list = baseMapper.countCustomerMake();
        List<String> data1List = new ArrayList<>();
        List<Long> data2List=new ArrayList<>();
        list.forEach(m->{
            data1List.add(m.get("level").toString());
            data2List.add((Long) m.get("total"));
        });
        return R.ok().put("data1",data1List).put("data2",data2List);
    }

    /**
     * 饼图数据
     *
     * @return
     */
    @Override
    public R countCustomerMake2() {
        List<Map<String,Object>> list = customerMapper.countCustomerMake();
        List<String> data1List = new ArrayList<>();
        List<Map<String,Object>> data2List = new ArrayList<>();
        list.forEach(m->{
            data1List.add(m.get("level").toString());
            Map<String,Object> temp=new HashMap<>(2);
            temp.put("name", m.get("level"));
            temp.put("value", m.get("total"));
            data2List.add(temp);
        });
        return R.ok().put("data1",data1List).put("data2",data2List);
    }

    /**
     * 分页条件查询客户贡献
     *
     * @param query
     * @return
     */
    @Override
    public PageUtils selectContributionPageByParam(ContributionQuery query) {
        Page<ContributionVO> pageParam = new Page<>(query.getPage(), query.getLimit());
        Page<ContributionVO> page = baseMapper.selectContributionPageByParam(pageParam, query);
        return new PageUtils(page);
    }

    private void appendWrapper(QueryWrapper<Customer> wrapper, CustomerQuery query) {
        wrapper.eq("state", CustomerStateEnums.UN_LOSS.getCode());
        if(StringUtils.hasLength(query.getKhno())){
            wrapper.eq("khno", query.getKhno());
        }
        if(StringUtils.hasLength(query.getName())){
            wrapper.like("name", query.getName());
        }
        if(StringUtils.hasLength(query.getLevel())){
            wrapper.eq("level", query.getLevel());
        }
    }
}




