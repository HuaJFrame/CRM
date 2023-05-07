package com.huajframe.xycrm.controller.cus;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.huajframe.xycrm.common.enums.CusLossStateEnums;
import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.common.util.RedisUtil;
import com.huajframe.xycrm.entity.CustomerLoss;
import com.huajframe.xycrm.query.CusLossQuery;
import com.huajframe.xycrm.service.CustomerLossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hua JFarmer
 */
@RestController
@RequestMapping("/loss")
public class CusLossController {

    @Autowired
    private CustomerLossService customerLossService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 列表
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('cus:lossRep:list')" + "||" + "hasAuthority('report:loss:list')")
    public R list(@RequestBody CusLossQuery query) {
        PageUtils page = customerLossService.selectPageByParam(query);
        return R.ok().put("page", page);
    }

    @GetMapping("/sureLoss/{id}")
    @PreAuthorize("hasAuthority('cus:loss:sureLoss')")
    public R updateDevResult(@PathVariable Long id) {
        boolean b = customerLossService.update(
                new UpdateWrapper<CustomerLoss>()
                        .set("state", CusLossStateEnums.SURE_LOSS.getCode())
                        .eq("id", id)
        );
        if(b){
            redisUtil.del("customer::" + id);
            return R.ok();
        }else{
            return R.ok("操作失败");
        }
    }
}
