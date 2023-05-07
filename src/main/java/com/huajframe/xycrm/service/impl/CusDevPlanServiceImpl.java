package com.huajframe.xycrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.common.util.RedisUtil;
import com.huajframe.xycrm.entity.CusDevPlan;
import com.huajframe.xycrm.query.CusDevPlanQuery;
import com.huajframe.xycrm.service.CusDevPlanService;
import com.huajframe.xycrm.mapper.CusDevPlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author Hua JFarmer
* @description 针对表【sys_cus_dev_plan】的数据库操作Service实现
* @createDate 2023-05-02 21:23:27
*/
@Service
public class CusDevPlanServiceImpl extends ServiceImpl<CusDevPlanMapper, CusDevPlan>
    implements CusDevPlanService{

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 分页条件查询
     *
     * @param query
     * @return
     */
    @Override
    public PageUtils selectPageByParam(CusDevPlanQuery query) {
        Page<CusDevPlan> pageParam = new Page<>(query.getPage(), query.getLimit());
        QueryWrapper<CusDevPlan> wrapper = new QueryWrapper<>();
        wrapper.eq("sale_chance_id", query.getSid());
        Page<CusDevPlan> page = baseMapper.selectPage(pageParam, wrapper);
        return new PageUtils(page);
    }

    /**
     * 添加开发计划
     *
     * @param cusDevPlan
     * @return
     */
    @Override
    public int addCusDevPlan(CusDevPlan cusDevPlan) {
        Date date = new Date();
        cusDevPlan.setCreateDate(date);
        cusDevPlan.setUpdateDate(date);
        return baseMapper.insert(cusDevPlan);
    }

    /**
     * 修改开发计划
     *
     * @param cusDevPlan
     * @return
     */
    @Override
    public int updateCusDevPlan(CusDevPlan cusDevPlan) {
        cusDevPlan.setUpdateDate(new Date());
        redisUtil.del("cusDevPlan::" + cusDevPlan.getId());
        return baseMapper.updateById(cusDevPlan);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @Override
    public R removeUserByIds(List<Long> ids) {
        removeByIds(ids);
        for (Long id : ids) {
            redisUtil.del("cusDevPlan::" + id);
        }
        return R.ok();
    }
}




