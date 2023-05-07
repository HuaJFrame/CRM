package com.huajframe.xycrm.controller.server;

import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.common.util.RedisUtil;
import com.huajframe.xycrm.entity.CustomerServe;
import com.huajframe.xycrm.query.CusServeQuery;
import com.huajframe.xycrm.service.CustomerServeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Hua JFarmer
 */
@RestController
@RequestMapping("/serve")
public class ServeController {

    @Autowired
    private CustomerServeService customerServeService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 列表
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('serve:query')")
    public R list(@RequestBody CusServeQuery query) {
        PageUtils page = customerServeService.selectPageByParam(query);
        return R.ok().put("page", page);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('serve:assign:do')" + "||" + "hasAuthority('serve:proce:do')" + "||"
            + "hasAuthority('serve:feed:do')")
    public R save(@RequestBody @Valid CustomerServe customerServe) {
        int result;
        if (customerServe.getId() == null || customerServe.getId() == 0) {
            result = customerServeService.addCustomerServe(customerServe);
        } else {
            result = customerServeService.updateCustomerServe(customerServe);
        }
        if(result == 1){
            return R.ok();
        }else{
            return R.error("操作失败");
        }
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('serve:query')")
    public R findById(@PathVariable(value = "id")Long id){
        CustomerServe customerServe1 = (CustomerServe) redisUtil.get("customerServe::" + id);
        if(customerServe1 != null){
            return R.ok().put("customerServe", customerServe1);
        }
        CustomerServe customerServe = customerServeService.getById(id);
        redisUtil.set("customerServe::" + id, customerServe, 20);
        return R.ok().put("customerServe", customerServe);
    }

    /**
     * 客户反馈分析，根据myd进行分析
     * 这是返回柱状图的数据
     * @return
     */
    @GetMapping("/countByMyd1")
    @PreAuthorize("hasAuthority('report:serve:list')")
    public R countByMyd(){
        return customerServeService.countByMyd();
    }


    /**
     * 客户反馈分析，根据myd进行分析
     * 这是返回饼图的数据
     * @return
     */
    @GetMapping("/countByMyd2")
    @PreAuthorize("hasAuthority('report:serve:list')")
    public R countByMyd2(){
        return customerServeService.countByMyd2();
    }
}
