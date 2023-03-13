package com.vito.portal.persistence.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author panjin
 */
@Data
@TableName(value = "tbl_user_layout")
public class UserLayoutDO {

    private Long id;
    private Long layoutId;
    private Long userId;
    private Long componentId;
    private Long tenantId;
    private Long createBy;
    private Long updateBy;
}
