package com.huajframe.xycrm.mapper;

import com.huajframe.xycrm.entity.CustomerServe;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
* @author Hua JFarmer
* @description 针对表【sys_customer_serve】的数据库操作Mapper
* @createDate 2023-05-02 21:23:27
* @Entity com.huajframe.xycrm.entity.CustomerServe
*/
public interface CustomerServeMapper extends BaseMapper<CustomerServe> {

    List<Map<String, Object>> countByMyd();
}




