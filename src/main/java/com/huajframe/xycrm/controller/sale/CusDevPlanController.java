package com.huajframe.xycrm.controller.sale;

import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.common.util.RedisUtil;
import com.huajframe.xycrm.entity.CusDevPlan;
import com.huajframe.xycrm.query.CusDevPlanQuery;
import com.huajframe.xycrm.service.CusDevPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * @author Hua JFarmer
 */
@RestController
@RequestMapping("/cusDevPlan")
public class CusDevPlanController {

    @Autowired
    private CusDevPlanService cusDevPlanService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 列表
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('sale:cusDevPlan:query')")
    public R list(@RequestBody @Valid CusDevPlanQuery query) {
        PageUtils page = cusDevPlanService.selectPageByParam(query);
        return R.ok().put("page", page);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sale:cusDevPlan:query')")
    public R findById(@PathVariable(value = "id")Long id){
        CusDevPlan cusDevPlan1 = (CusDevPlan) redisUtil.get("cusDevPlan::" + id);
        if(cusDevPlan1 != null){
            return R.ok().put("cusDevPlan", cusDevPlan1);
        }
        CusDevPlan cusDevPlan = cusDevPlanService.getById(id);
        redisUtil.set("cusDevPlan::" + id, cusDevPlan, 10);
        return R.ok().put("cusDevPlan", cusDevPlan);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sale:cusDevPlan:add')" + "||" + "hasAuthority('sale:cusDevPlan:edit')")
    public R save(@RequestBody @Valid CusDevPlan cusDevPlan) {
        int result;
        if (cusDevPlan.getId() == null || cusDevPlan.getId() == 0) {
            result = cusDevPlanService.addCusDevPlan(cusDevPlan);
        } else {
            result = cusDevPlanService.updateCusDevPlan(cusDevPlan);
        }
        if(result == 1){
            return R.ok();
        }else{
            return R.error("操作失败");
        }
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('sale:cusDevPlan:delete')")
    public R delete(Long[] ids){
        return cusDevPlanService.removeUserByIds(Arrays.asList(ids));
    }
}
