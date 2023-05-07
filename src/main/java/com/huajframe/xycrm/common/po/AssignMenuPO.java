package com.huajframe.xycrm.common.po;

import lombok.Data;

import java.util.List;

/**
 * @author Hua JFarmer
 */
@Data
public class AssignMenuPO {
    private Long roleId;
    private List<Long> ids;
}
