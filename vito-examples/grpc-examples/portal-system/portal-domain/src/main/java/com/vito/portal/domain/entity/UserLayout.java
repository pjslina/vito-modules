package com.vito.portal.domain.entity;

import lombok.Data;

/**
 * @author panjin
 */
@Data
public class UserLayout {

    private Long layoutId;
    private Long userId;
    private Long componentId;
    private Long tenantId;
    private Long createBy;
    private Long updateBy;
}
