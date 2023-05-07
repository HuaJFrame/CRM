package com.huajframe.xycrm.common.enums;

import lombok.Getter;

/**
 * 用户状态枚举类
 * @author Hua JFarmer
 */
@Getter
public enum UserStatusEnum {
    /**
     * 正常
     */
    NORMAL(0),
    /**
     * 禁用
     */
    DISABLED(1);

    private final Integer code;

    UserStatusEnum(int code) {
        this.code = code;
    }
}
