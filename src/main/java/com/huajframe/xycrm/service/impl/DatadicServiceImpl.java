package com.huajframe.xycrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.common.util.RedisUtil;
import com.huajframe.xycrm.entity.Datadic;
import com.huajframe.xycrm.entity.Role;
import com.huajframe.xycrm.entity.User;
import com.huajframe.xycrm.query.DictQuery;
import com.huajframe.xycrm.service.DatadicService;
import com.huajframe.xycrm.mapper.DatadicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
* @author Hua JFarmer
* @description 针对表【sys_datadic】的数据库操作Service实现
* @createDate 2023-05-02 21:23:27
*/
@Service
public class DatadicServiceImpl extends ServiceImpl<DatadicMapper, Datadic>
    implements DatadicService{

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 返回字典名称列表
     *
     * @return
     */
    @Override
    public R nameList() {
        List<String> list = (List<String>) redisUtil.get("dictNameList");
        if(list != null){
            return R.ok().put("dictNameList", list);
        }
        List<String> nameList = baseMapper.nameList();
        redisUtil.set("dictNameList", nameList, 8);
        return R.ok().put("dictNameList", nameList);
    }

    /**
     * 分页条件查询
     *
     * @param query
     * @return
     */
    @Override
    public PageUtils selectPageByParam(DictQuery query) {
        Page<Datadic> pageParam = new Page<>(query.getPage(), query.getLimit());
        QueryWrapper<Datadic> wrapper = new QueryWrapper<>();
        appendWrapper(wrapper, query);
        Page<Datadic> dictPage = baseMapper.selectPage(pageParam, wrapper);
        return new PageUtils(dictPage);
    }

    /**
     * 新建字典
     *
     * @param datadic
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addDict(Datadic datadic) {
        Date date = new Date();
        datadic.setCreateDate(date);
        datadic.setUpdateDate(date);
        redisUtil.del("dictNameList");
        return baseMapper.insert(datadic);
    }

    /**
     * 更新字典
     *
     * @param datadic
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateDict(Datadic datadic) {
        datadic.setUpdateDate(new Date());
        redisUtil.del("dictNameList");
        redisUtil.del("dict::" + datadic.getId());
        return baseMapper.updateById(datadic);
    }

    /**
     * 删除字典
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R removeDictByIds(List<Long> ids) {
        redisUtil.del("dictNameList");
        for (Long id : ids) {
            redisUtil.del("dict::" + id);
        }
        baseMapper.deleteBatchIds(ids);
        return R.ok();
    }

    /**
     * 根据字典dictname查询dictValue
     *
     * @param dictName
     * @return
     */
    @Override
    public R selectDictValue(String dictName) {
        if(StringUtils.isEmpty(dictName)){
            return R.error("请输入字典名称");
        }
        List<Datadic> dictValues = (List<Datadic>) redisUtil.get("dictValue::" + dictName);
        if(dictValues != null){
            return R.ok().put("dictValues", dictValues);
        }
        List<Datadic> datadics = baseMapper.selectList(new QueryWrapper<Datadic>().eq("data_dic_name", dictName));
        redisUtil.set("dictValue::" + dictName, datadics, 10);
        return R.ok().put("dictValues", datadics);
    }

    /**
     * 条件构造wrapper
     * @param wrapper
     * @param query
     */
    private void appendWrapper(QueryWrapper<Datadic> wrapper, DictQuery query) {
        if(StringUtils.hasLength(query.getDataDicName())){
            wrapper.eq("data_dic_name", query.getDataDicName());
        }

        if(StringUtils.hasLength(query.getDataDicValue())){
            wrapper.likeRight("data_dic_value", query.getDataDicValue());
        }
    }
}




