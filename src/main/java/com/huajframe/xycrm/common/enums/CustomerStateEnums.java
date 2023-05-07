package com.huajframe.xycrm.common.enums;

import lombok.Getter;

/**
 * @author Hua JFarmer
 */
@Getter
public enum CustomerStateEnums {
    /**
     * 未流失
     */
    UN_LOSS(0),
    /**
     * 确认暂缓流失，数据插入到客户流失暂缓表
     */
    SURE_LOSS(1);

    private final int code;

    CustomerStateEnums(int code) {
        this.code = code;
    }
}
