package com.huajframe.xycrm.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huajframe.xycrm.common.vo.ContributionVO;
import com.huajframe.xycrm.entity.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huajframe.xycrm.query.ContributionQuery;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author Hua JFarmer
* @description 针对表【sys_customer】的数据库操作Mapper
* @createDate 2023-05-02 21:23:27
* @Entity com.huajframe.xycrm.entity.Customer
*/
public interface CustomerMapper extends BaseMapper<Customer> {

    /**
     * 查询流失客户
     * 流失客户数据：
     *   1.创建时间大于6个月
     *   2.最新的订单时间是是6个月前创建的
     * @return
     */
    List<Customer> queryLossCustomer();

    /**
     * 查询用户名称列表
     * @return
     */
    List<String> selectCustomerList();

    /**
     * 查询客户级别以及对于客户数量
     * @return
     */
    @MapKey("level")
    List<Map<String, Object>> countCustomerMake();


    Page<ContributionVO> selectContributionPageByParam(Page<ContributionVO> page, @Param("query") ContributionQuery query);
}




