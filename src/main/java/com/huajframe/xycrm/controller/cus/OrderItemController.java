package com.huajframe.xycrm.controller.cus;

import com.huajframe.xycrm.common.util.PageUtils;
import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.query.OrderItemQuery;
import com.huajframe.xycrm.service.OrderDetailsService;
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
@RequestMapping("/orderItem")
@RestController
public class OrderItemController{

    @Autowired
    private OrderDetailsService orderDetailsService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('cus:orderItem:list')")
    public R list(@RequestBody @Valid OrderItemQuery query) {
        PageUtils page = orderDetailsService.selectPageByParam(query);
        return R.ok().put("page", page);
    }
}
