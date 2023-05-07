package com.huajframe.xycrm.mapper;

import com.huajframe.xycrm.entity.Datadic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Hua JFarmer
* @description 针对表【sys_datadic】的数据库操作Mapper
* @createDate 2023-05-02 21:23:27
* @Entity com.huajframe.xycrm.entity.Datadic
*/
public interface DatadicMapper extends BaseMapper<Datadic> {

    /**
     * 回字典名称列表
     * @return
     */
    List<String> nameList();
}




