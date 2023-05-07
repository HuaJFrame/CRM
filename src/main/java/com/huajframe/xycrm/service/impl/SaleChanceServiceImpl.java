package com.huajframe.xycrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huajframe.xycrm.common.enums.ChanceStateEnums;
import com.huajframe.xycrm.common.enums.CustomerStateEnums;
import com.huajframe.xycrm.common.enums.DevResultEnums;
import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.common.util.RedisUtil;
import com.huajframe.xycrm.entity.SaleChance;
import com.huajframe.xycrm.query.SaleChanceQuery;
import com.huajframe.xycrm.service.SaleChanceService;
import com.huajframe.xycrm.mapper.SaleChanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
* @author Hua JFarmer
* @description 针对表【sys_sale_chance】的数据库操作Service实现
* @createDate 2023-05-02 21:23:27
*/
@Service
public class SaleChanceServiceImpl extends ServiceImpl<SaleChanceMapper, SaleChance>
    implements SaleChanceService{

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 分页条件查询
     *
     * @param query
     * @return
     */
    @Override
    public PageUtils selectPageByParam(SaleChanceQuery query) {
        Page<SaleChance> pageParam = new Page<>(query.getPage(), query.getLimit());
        QueryWrapper<SaleChance> wrapper = new QueryWrapper<>();
        appendWrapper(wrapper, query);
        Page<SaleChance> page = baseMapper.selectPage(pageParam, wrapper);
        return new PageUtils(page);
    }

    /**
     * 添加机会
     *
     * @param saleChance
     * @return
     */
    @Override
    public int addChance(SaleChance saleChance) {
        //默认未分配
        saleChance.setState(ChanceStateEnums.UN_ASSIGN.getCode());
        //开发状态为未开发
        saleChance.setDevResult(DevResultEnums.UNDEV.getStatus());
        if(!StringUtils.isEmpty(saleChance.getAssignMan())){
            //如果分配人不为空
            // 已分配
            saleChance.setState(ChanceStateEnums.ASSIGNED.getCode());
            // 开发中
            saleChance.setDevResult(DevResultEnums.DEVING.getStatus());
            //分配时间为现在
            saleChance.setAssignTime(new Date());
        }
        //默认为有效
        //创建和修改时间默认为现在时间
        Date date = new Date();
        saleChance.setCreateDate(date);
        saleChance.setUpdateDate(date);
        return baseMapper.insert(saleChance);
    }

    /**
     * 更新机会
     *
     * @param saleChance
     * @return
     */
    @Override
    public int updateChance(SaleChance saleChance) {
        //数据库查询
        SaleChance temp = baseMapper.selectById(saleChance.getId());
        saleChance.setUpdateDate(new Date());
        if(StringUtils.isEmpty(temp.getAssignMan()) &&
                !StringUtils.isEmpty(saleChance.getAssignMan())){
            //之前未分配，现在已分配
            saleChance.setState(ChanceStateEnums.ASSIGNED.getCode());
            saleChance.setAssignTime(new Date());
            saleChance.setDevResult(DevResultEnums.DEVING.getStatus());
        }else if(!StringUtils.isEmpty(temp.getAssignMan()) &&
                StringUtils.isEmpty(saleChance.getAssignMan())) {
            //之前已经分配，现在没有分配了
            saleChance.setState(ChanceStateEnums.UN_ASSIGN.getCode());
            saleChance.setAssignTime(null);
            saleChance.setDevResult(DevResultEnums.UNDEV.getStatus());
            saleChance.setAssignMan("");
        }else if(!temp.getAssignMan().equals(saleChance.getAssignMan())){
            //两次分配人不同
            saleChance.setAssignTime(new Date());
        }else{
            //以前没分配现在也没有，或以前分配过现在没变化
        }
        redisUtil.del("chance::" + saleChance.getId());
        return baseMapper.updateById(saleChance);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R removeUserByIds(List<Long> ids) {
        removeByIds(ids);
        for (Long id : ids) {
            redisUtil.del("chance::" + id);
        }
        return R.ok();
    }

    /**
     * 拼接wrapper
     * @param wrapper
     * @param query
     */
    private void appendWrapper(QueryWrapper<SaleChance> wrapper, SaleChanceQuery query) {
        if(query.getState() != null){
            wrapper.eq("state", query.getState());
        }
        if(StringUtils.hasLength(query.getCustomerName())){
            wrapper.like("customer_name", query.getCustomerName());
        }
        if(query.getDevResult() != null){
            wrapper.eq("dev_result", query.getDevResult());
        }
        if(StringUtils.hasLength(query.getAssignMan())){
            wrapper.eq("assign_man", query.getAssignMan());
        }
    }
}




