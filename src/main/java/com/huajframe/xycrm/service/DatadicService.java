package com.huajframe.xycrm.service;

import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.entity.Datadic;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huajframe.xycrm.query.DictQuery;

import java.util.List;

/**
* @author Hua JFarmer
* @description 针对表【sys_datadic】的数据库操作Service
* @createDate 2023-05-02 21:23:27
*/
public interface DatadicService extends IService<Datadic> {

    /**
     * 返回字典名称列表
     * @return
     */
    R nameList();

    /**
     * 分页条件查询
     * @param query
     * @return
     */
    PageUtils selectPageByParam(DictQuery query);

    /**
     * 新建字典
     * @param datadic
     * @return
     */
    int addDict(Datadic datadic);

    /**
     * 更新字典
     * @param datadic
     * @return
     */
    int updateDict(Datadic datadic);

    /**
     * 删除字典
     * @param ids
     * @return
     */
    R removeDictByIds(List<Long> ids);

    /**
     * 根据字典dictname查询dictValue
     * @param dictName
     * @return
     */
    R selectDictValue(String dictName);
}
