package com.vito.portal.api;

import com.vito.framework.dto.Response;
import com.vito.portal.dto.UserLayoutAddCmd;

/**
 * @author panjin
 */
public interface LayoutService {

    /**
     * 用户添加布局
     * @param cmd
     * @return
     */
    Response addUserLayout(UserLayoutAddCmd cmd);
}
