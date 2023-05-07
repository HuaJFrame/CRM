package com.huajframe.xycrm.query;

import com.huajframe.xycrm.common.util.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Hua JFarmer
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ContributionQuery extends BaseQuery {

    private String name;

    // 1  0-1000   2 1000-3000  3  3000-5000  4 50000
    private String type;

}
