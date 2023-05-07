package com.huajframe.xycrm.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author Hua JFarmer
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVO {
    private Long userId;
    private String username;
    private Set<String> permissions;
}
