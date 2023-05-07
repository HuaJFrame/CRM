package com.huajframe.xycrm.common.enums;

import lombok.Getter;

/**
 * 营销机会分配状态
 * @author Hua JFarmer
 */
@Getter
public enum ChanceStateEnums {
    /**
     * 未流失，属于暂缓流失
     */
    UN_ASSIGN(0),
    /**
     * 确认流失
     */
    ASSIGNED(1);

    private final int code;

    ChanceStateEnums(int code) {
        this.code = code;
    }
}
