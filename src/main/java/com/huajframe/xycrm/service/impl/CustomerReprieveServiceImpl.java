package com.huajframe.xycrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.common.util.RedisUtil;
import com.huajframe.xycrm.entity.Customer;
import com.huajframe.xycrm.entity.CustomerReprieve;
import com.huajframe.xycrm.query.CusReprieveQuery;
import com.huajframe.xycrm.service.CustomerReprieveService;
import com.huajframe.xycrm.mapper.CustomerReprieveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
* @author Hua JFarmer
* @description 针对表【sys_customer_reprieve】的数据库操作Service实现
* @createDate 2023-05-02 21:23:27
*/
@Service
public class CustomerReprieveServiceImpl extends ServiceImpl<CustomerReprieveMapper, CustomerReprieve>
    implements CustomerReprieveService{

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 分页条件查询
     *
     * @param query
     * @return
     */
    @Override
    public PageUtils selectPageByParam(CusReprieveQuery query) {
        Page<CustomerReprieve> pageParam = new Page<>(query.getPage(), query.getLimit());
        QueryWrapper<CustomerReprieve> wrapper = new QueryWrapper<>();
        wrapper.eq("loss_id", query.getLossId());
        Page<CustomerReprieve> page = baseMapper.selectPage(pageParam, wrapper);
        return new PageUtils(page);
    }

    /**
     * 添加
     *
     * @param customerReprieve
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addCusRepr(CustomerReprieve customerReprieve) {
        Date date = new Date();
        customerReprieve.setCreateDate(date);
        customerReprieve.setUpdateDate(date);
        return baseMapper.insert(customerReprieve);
    }

    /**
     * 修改
     *
     * @param customerReprieve
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateCusRepr(CustomerReprieve customerReprieve) {
        customerReprieve.setUpdateDate(new Date());
        customerReprieve.setMeasure(customerReprieve.getMeasure());
        redisUtil.del("customerReprieve::" + customerReprieve.getId());
        return baseMapper.updateById(customerReprieve);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @Override
    public R removeReprByIds(List<Long> ids) {
        removeByIds(ids);
        for (Long id : ids) {
            redisUtil.del("customerReprieve::" + id);
        }
        return R.ok();
    }
}




