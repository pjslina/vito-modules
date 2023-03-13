package com.vito.portal.dto.req;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author panjin
 */
@Data
public class UserLayoutReq extends AbstractLayoutReq {

    /** 布局对应的组件列表 */
    @NotNull
    private List<ComponentReq> componentList;
    /** 布局Id，如果不为空，则是修改，为空，则为编辑*/
    private Long layoutId;

}
