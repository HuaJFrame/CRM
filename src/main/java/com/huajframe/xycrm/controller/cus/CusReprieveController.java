package com.huajframe.xycrm.controller.cus;

import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.common.util.RedisUtil;
import com.huajframe.xycrm.entity.CustomerReprieve;
import com.huajframe.xycrm.query.CusReprieveQuery;
import com.huajframe.xycrm.service.CustomerReprieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * @author Hua JFarmer
 */
@RestController
@RequestMapping("/cusRep")
public class CusReprieveController {

    @Autowired
    private CustomerReprieveService customerReprieveService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 列表
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('cus:lossRep:list')")
    public R list(@RequestBody CusReprieveQuery query) {
        PageUtils page = customerReprieveService.selectPageByParam(query);
        return R.ok().put("page", page);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('cus:lossRep:list')")
    public R findById(@PathVariable(value = "id")Long id){
        CustomerReprieve customerReprieve1 = (CustomerReprieve) redisUtil.get("customerReprieve::" + id);
        if(customerReprieve1 != null){
            return R.ok().put("customerReprieve", customerReprieve1);
        }
        CustomerReprieve customerReprieve = customerReprieveService.getById(id);
        redisUtil.set("customerReprieve::" + id, customerReprieve, 10);
        return R.ok().put("customerReprieve", customerReprieve);
    }


    @PostMapping("/save")
    @PreAuthorize("hasAuthority('cus:lossRep:add')" + "||" + "hasAuthority('cus:lossRep:edit')")
    public R save(@RequestBody @Valid CustomerReprieve customerReprieve) {
        int result;
        if (customerReprieve.getId() == null || customerReprieve.getId() == 0) {
            result = customerReprieveService.addCusRepr(customerReprieve);
        } else {
            result = customerReprieveService.updateCusRepr(customerReprieve);
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
    @PreAuthorize("hasAuthority('cus:lossRep:delete')")
    public R delete(Long[] ids){
        return customerReprieveService.removeReprByIds(Arrays.asList(ids));
    }
}
