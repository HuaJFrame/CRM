package com.huajframe.xycrm.controller.sys;

import com.huajframe.xycrm.common.util.R;
import com.huajframe.xycrm.common.util.RedisUtil;
import com.huajframe.xycrm.entity.Menu;
import com.huajframe.xycrm.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hua JFarmer
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('system:menu:list')")
    public R list(){
        return menuService.menuTree();
    }

    /**
     * 添加或者修改
     * @param menu
     * @return
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('system:menu:add')"+"||"+"hasAuthority('system:menu:edit')")
    public R save(@RequestBody Menu menu){
        int result = 0;
        if(menu.getId() == null || menu.getId() == 0){
            result = menuService.addMenu(menu);
        }else{
            result = menuService.updateMenu(menu);
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
    @PreAuthorize("hasAuthority('system:menu:query')")
    public R findById(@PathVariable(value = "id")Long id){
        Menu menu = (Menu) redisUtil.get("menu::" + id);
        if(menu != null){
            return R.ok().put("menu", menu);
        }
        Menu menu1 = menuService.getById(id);
        redisUtil.set("menu::" + id, menu1, 20);
        return R.ok().put("menu", menu1);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('system:menu:delete')")
    public R delete(@PathVariable(value = "id") Long id){
        return menuService.deleteById(id);
    }
}
