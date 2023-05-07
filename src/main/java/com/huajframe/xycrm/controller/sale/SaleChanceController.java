package com.huajframe.xycrm.controller.sale;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.common.util.RedisUtil;
import com.huajframe.xycrm.entity.SaleChance;
import com.huajframe.xycrm.query.SaleChanceQuery;
import com.huajframe.xycrm.service.SaleChanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * 营销机会管理
 * @author Hua JFarmer
 */
@RestController
@RequestMapping("/chance")
public class SaleChanceController {

    @Autowired
    private SaleChanceService saleChanceService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 列表
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('sale:chance:query')")
    public R list(@RequestBody SaleChanceQuery query) {
        PageUtils page = saleChanceService.selectPageByParam(query);
        return R.ok().put("page", page);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sale:chance:query')")
    public R findById(@PathVariable(value = "id")Long id){
        SaleChance saleChance1 = (SaleChance) redisUtil.get("chance::" + id);
        if(saleChance1 != null){
            return R.ok().put("saleChance", saleChance1);
        }
        SaleChance saleChance = saleChanceService.getById(id);
        redisUtil.set("chance::" + id, saleChance, 20);
        return R.ok().put("saleChance", saleChance);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sale:chance:add')" + "||" + "hasAuthority('sale:chance:edit')")
    public R save(@RequestBody @Valid SaleChance saleChance) {
        int result;
        if (saleChance.getId() == null || saleChance.getId() == 0) {
            result = saleChanceService.addChance(saleChance);
        } else {
            result = saleChanceService.updateChance(saleChance);
        }
        if(result == 1){
            return R.ok();
        }else{
            return R.error("操作失败");
        }
    }


    /**
     * 删除
     */
    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('sale:chance:delete')")
    public R delete(Long[] ids){
        return saleChanceService.removeUserByIds(Arrays.asList(ids));
    }


    @GetMapping("/devResult/{id}/{result}")
    @PreAuthorize("hasAuthority('sale:chance:edit')")
    public R updateDevResult(@PathVariable Long id, @PathVariable Integer result) {
        boolean b = saleChanceService.update(
                new UpdateWrapper<SaleChance>()
                        .set("dev_result", result)
                        .eq("id", id)
        );
        if(b){
            redisUtil.del("chance::" + id);
            return R.ok();
        }else{
            return R.ok("操作失败");
        }
    }
}
