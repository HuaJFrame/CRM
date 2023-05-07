package com.huajframe.xycrm.controller.sys;

import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.common.util.RedisUtil;
import com.huajframe.xycrm.entity.Datadic;
import com.huajframe.xycrm.query.DictQuery;
import com.huajframe.xycrm.service.DatadicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * 数据字典
 * @author Hua JFarmer
 */
@RestController
@RequestMapping("/dict")
public class DatadicController {

    @Autowired
    private DatadicService datadicService;
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/nameList")
    @PreAuthorize("hasAuthority('sys:dict:list')")
    public R nameList(){
        return datadicService.nameList();
    }

    @GetMapping("/dictValue")
    public R dictValue(@RequestParam("dictName") String dictName){
        return datadicService.selectDictValue(dictName);
    }

    /**
     * 列表
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('system:dict:query')")
    public R list(@RequestBody DictQuery dictQuery) {
        PageUtils page = datadicService.selectPageByParam(dictQuery);
        return R.ok().put("page", page);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('system:dict:add')" + "||" + "hasAuthority('system:dict:edit')")
    public R save(@RequestBody @Valid Datadic datadic) {
        int result;
        if (datadic.getId() == null || datadic.getId() == 0) {
            result = datadicService.addDict(datadic);
        } else {
            result = datadicService.updateDict(datadic);
        }
        if(result == 1){
            return R.ok();
        }else{
            return R.error("操作失败");
        }
    }

    /**
     * 根据id查询字典数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('system:dict:query')")
    public R findById(@PathVariable(value = "id") Long id){
        Datadic dict = (Datadic) redisUtil.get("dict::" + id);
        if(dict != null){
            return R.ok().put("dict", dict);
        }
        Datadic datadic = datadicService.getById(id);
        redisUtil.set("dict::" + id, datadic, 15);
        return R.ok().put("dict", datadic);
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('system:dict:delete')")
    public R delete(Long[] ids){
        return datadicService.removeDictByIds(Arrays.asList(ids));
    }
}
