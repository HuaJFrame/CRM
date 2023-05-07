package com.huajframe.xycrm.common.enums;

import lombok.Getter;

/**
 * 开发状态枚举
 * @author Hua JFarmer
 */
@Getter
public enum DevResultEnums {
    /**
     * 未开发
     */
    UNDEV(0),
    /**
     * 开发中
     */
    DEVING(1),
    /**
     * 开发成功
     */
    DEV_SUCCESS(2),
    /**
     * 开发失败
     */
    DEV_FAILED(3);

    private final Integer status;

    DevResultEnums(Integer status) {
        this.status = status;
    }
}
