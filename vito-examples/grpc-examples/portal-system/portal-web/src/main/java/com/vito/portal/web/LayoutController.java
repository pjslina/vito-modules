package com.vito.portal.web;

import com.vito.framework.dto.Response;
import com.vito.framework.dto.SingleResponse;
import com.vito.portal.api.LayoutService;
import com.vito.portal.dto.UserLayoutAddCmd;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author panjin
 */
@RestController
public class LayoutController {

    @Resource
    private LayoutService layoutService;

    @GetMapping(value = "/layout")
    public SingleResponse userLayout(@RequestParam Long userId) {
        return SingleResponse.success();
    }

    @PostMapping(value = "/layout")
    public Response addUserLayout(@RequestBody UserLayoutAddCmd userLayoutAddCmd) {
        return layoutService.addUserLayout(userLayoutAddCmd);
    }
}
