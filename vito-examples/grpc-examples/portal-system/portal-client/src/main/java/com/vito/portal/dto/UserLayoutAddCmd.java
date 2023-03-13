package com.vito.portal.dto;

import com.vito.portal.dto.req.UserLayoutReq;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author panjin
 */
@Data
public class UserLayoutAddCmd extends CommonCommand {

    @NotNull
    private UserLayoutReq userLayoutReq;
}
