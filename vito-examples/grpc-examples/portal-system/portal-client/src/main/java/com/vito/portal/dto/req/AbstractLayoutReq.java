package com.vito.portal.dto.req;

import com.vito.framework.dto.ClientObject;
import lombok.Data;

/**
 * @author panjin
 */
@Data
public abstract class AbstractLayoutReq extends ClientObject {

    /** 用户Id */
    private Long userId;
    /** 租户Id */
    private Long tenantId;

    private Long createBy;

    private Long updateBy;
}
