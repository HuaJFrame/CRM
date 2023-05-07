package com.huajframe.xycrm.controller.cus;

import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.common.util.RedisUtil;
import com.huajframe.xycrm.entity.Customer;
import com.huajframe.xycrm.query.ContributionQuery;
import com.huajframe.xycrm.query.CustomerQuery;
import com.huajframe.xycrm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * @author Hua JFarmer
 */
@RestController
@RequestMapping("/cus")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 列表
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('cus:info:query')")
    public R list(@RequestBody CustomerQuery query) {
        PageUtils page = customerService.selectPageByParam(query);
        return R.ok().put("page", page);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('cus:info:query')")
    public R findById(@PathVariable(value = "id")Long id){
        Customer customer1 = (Customer) redisUtil.get("customer::" + id);
        if(customer1 != null){
            return R.ok().put("customer", customer1);
        }
        Customer customer = customerService.getById(id);
        redisUtil.set("customer::" + id, customer, 20);
        return R.ok().put("customer", customer);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('cus:info:add')" + "||" + "hasAuthority('cus:info:edit')")
    public R save(@RequestBody @Valid Customer customer) {
        int result;
        if (customer.getId() == null || customer.getId() == 0) {
            result = customerService.addCustomer(customer);
        } else {
            result = customerService.updateCustomer(customer);
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
    @PreAuthorize("hasAuthority('cus:info:delete')")
    public R delete(Long[] ids){
        return customerService.removeUserByIds(Arrays.asList(ids));
    }

    @GetMapping("/customerList")
    public R customerList(){
        return customerService.selectCustomerList();
    }


    /**
     * 客户组成分析，根据客户的level进行分析
     * 这是返回折线图的接口
     * @return
     */
    @GetMapping("/countCustomerMake1")
    @PreAuthorize("hasAuthority('report:make:list')")
    public R countCustomerMake(){
        return customerService.countCustomerMake1();
    }


    /**
     * 客户组成分析，根据客户的level进行分析
     * 这是返回饼图的接口
     * @return
     */
    @GetMapping("/countCustomerMake2")
    @PreAuthorize("hasAuthority('report:serve:list')")
    public R countCustomerMake02() {
        return customerService.countCustomerMake2();
    }

    /**
     * 分页条件查询用户贡献
     * @param query
     * @return
     */
    @PostMapping("/contribution")
    @PreAuthorize("hasAuthority('report:contrib:list')")
    public R contribution(@RequestBody ContributionQuery query){
        PageUtils page = customerService.selectContributionPageByParam(query);
        return R.ok().put("page", page);
    }
}
