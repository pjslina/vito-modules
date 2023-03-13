package com.vito.portal.command;

import com.vito.framework.dto.Response;
import com.vito.portal.domain.entity.UserLayout;
import com.vito.portal.dto.UserLayoutAddCmd;
import com.vito.portal.dto.req.UserLayoutReq;
import com.vito.portal.repository.UserLayoutRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author panjin
 */
@Component
public class LayoutAddCmdExe {

    @Resource
    private UserLayoutRepository layoutRepository;

    public Response execute(UserLayoutAddCmd cmd) {
        UserLayoutReq userLayoutReq = cmd.getUserLayoutReq();
        List<UserLayout> userLayoutList = new ArrayList<>(userLayoutReq.getComponentList().size());
        userLayoutReq.getComponentList().stream().forEach(f -> {
            UserLayout userLayout = new UserLayout();
            BeanUtils.copyProperties(userLayoutReq, userLayout);
            userLayout.setComponentId(f.getComponentId());
            userLayoutList.add(userLayout);
        });
        layoutRepository.saveOrUpdateBatch(userLayoutList);
        return Response.success();
    }
}
