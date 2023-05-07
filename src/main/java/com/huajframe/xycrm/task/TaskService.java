package com.huajframe.xycrm.task;

import com.huajframe.xycrm.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Hua JFarmer
 */
@Service
@Slf4j
public class TaskService {

    @Autowired
    private CustomerService customerService;

    /**
     * 定时任务检测流失客户
     */
    // @Scheduled(cron = "0/10 * * * * ?") //方便观察数据
    @Scheduled(cron = "0 0 0 * * *")  //每天0点开始执行
    public void job(){
        log.info("定时任务开始执行-->检查客户是否流失");
        customerService.updateCustomerState();
    }
}
