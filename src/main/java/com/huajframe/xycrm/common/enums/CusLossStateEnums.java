package com.huajframe.xycrm.common.enums;

import lombok.Getter;

/**
 * 用户暂缓流失表状态枚举
 * @author Hua JFarmer
 */
@Getter
public enum CusLossStateEnums {
    /**
     * 未流失，属于暂缓流失
     */
    UN_LOSS(0),
    /**
     * 确认流失
     */
    SURE_LOSS(1);

    private final int code;

    CusLossStateEnums(int code) {
        this.code = code;
    }
}
