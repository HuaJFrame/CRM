package com.huajframe.xycrm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Hua JFarmer
 */
@SpringBootApplication
@MapperScan(basePackages = "com.huajframe.xycrm.mapper")
public class XycrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(XycrmApplication.class, args);
    }

}
