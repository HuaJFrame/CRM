package com.huajframe.xycrm.service;

import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.entity.CusDevPlan;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huajframe.xycrm.query.CusDevPlanQuery;

import java.util.List;

/**
* @author Hua JFarmer
* @description 针对表【sys_cus_dev_plan】的数据库操作Service
* @createDate 2023-05-02 21:23:27
*/
public interface CusDevPlanService extends IService<CusDevPlan> {

    /**
     * 分页条件查询
     * @param query
     * @return
     */
    PageUtils selectPageByParam(CusDevPlanQuery query);

    /**
     * 添加开发计划
     * @param cusDevPlan
     * @return
     */
    int addCusDevPlan(CusDevPlan cusDevPlan);

    /**
     * 修改开发计划
     * @param cusDevPlan
     * @return
     */
    int updateCusDevPlan(CusDevPlan cusDevPlan);

    /**
     * 删除
     * @param ids
     * @return
     */
    R removeUserByIds(List<Long> ids);
}
