package com.huajframe.xycrm.controller.cus;

import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.query.CustomerQuery;
import com.huajframe.xycrm.query.OrderQuery;
import com.huajframe.xycrm.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Hua JFarmer
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('cus:order:list')")
    public R list(@RequestBody @Valid OrderQuery query) {
        PageUtils page = customerOrderService.selectPageByParam(query);
        return R.ok().put("page", page);
    }
}
