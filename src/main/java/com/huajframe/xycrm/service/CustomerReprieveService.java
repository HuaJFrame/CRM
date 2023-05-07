package com.huajframe.xycrm.service;

import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.entity.CustomerReprieve;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huajframe.xycrm.query.CusReprieveQuery;

import java.util.List;

/**
* @author Hua JFarmer
* @description 针对表【sys_customer_reprieve】的数据库操作Service
* @createDate 2023-05-02 21:23:27
*/
public interface CustomerReprieveService extends IService<CustomerReprieve> {

    /**
     * 分页条件查询
     * @param query
     * @return
     */
    PageUtils selectPageByParam(CusReprieveQuery query);

    /**
     * 添加
     * @param customerReprieve
     * @return
     */
    int addCusRepr(CustomerReprieve customerReprieve);

    /**
     * 修改
     * @param customerReprieve
     * @return
     */
    int updateCusRepr(CustomerReprieve customerReprieve);

    /**
     * 删除
     * @param ids
     * @return
     */
    R removeReprByIds(List<Long> ids);
}
