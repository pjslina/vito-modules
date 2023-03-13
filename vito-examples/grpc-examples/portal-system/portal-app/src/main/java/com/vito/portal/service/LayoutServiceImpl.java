package com.vito.portal.service;

import com.vito.framework.dto.Response;
import com.vito.portal.api.LayoutService;
import com.vito.portal.command.LayoutAddCmdExe;
import com.vito.portal.dto.UserLayoutAddCmd;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author panjin
 */
@Service
public class LayoutServiceImpl implements LayoutService {

    @Resource
    private LayoutAddCmdExe layoutAddCmdExe;

    @Override
    public Response addUserLayout(UserLayoutAddCmd cmd) {
        return layoutAddCmdExe.execute(cmd);
    }
}
