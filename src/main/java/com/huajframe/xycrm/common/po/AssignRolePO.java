package com.huajframe.xycrm.common.po;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Hua JFarmer
 */
@Data
public class AssignRolePO {
    @NotNull(message = "用户id不能为空")
    private Long id;
    private List<Long> roles;
}
