package com.huajframe.xycrm.service;

import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.entity.SaleChance;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huajframe.xycrm.query.SaleChanceQuery;

import java.util.List;

/**
* @author Hua JFarmer
* @description 针对表【sys_sale_chance】的数据库操作Service
* @createDate 2023-05-02 21:23:27
*/
public interface SaleChanceService extends IService<SaleChance> {

    /**
     * 分页条件查询
     * @param query
     * @return
     */
    PageUtils selectPageByParam(SaleChanceQuery query);

    /**
     * 添加机会
     * @param saleChance
     * @return
     */
    int addChance(SaleChance saleChance);

    /**
     * 更新机会
     * @param saleChance
     * @return
     */
    int updateChance(SaleChance saleChance);

    /**
     * 删除
     * @param ids
     * @return
     */
    R removeUserByIds(List<Long> ids);
}
