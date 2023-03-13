package com.vito.portal.dto.req;

import com.vito.framework.dto.ClientObject;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author panjin
 */
@Data
public class ComponentReq extends ClientObject {

    /** 组件标识 */
    @NotNull
    private Long componentId;

    /** 横坐标 */
    @NotNull
    @Min(value = 0, message = "组件横坐标不能小于0")
    @Max(value = 12, message = "组件横坐标不能大于12")
    private Integer abscissa;
    /** 纵坐标 */
    @NotNull
    @Min(value = 0, message = "组件纵坐标不能小于0")
    private Integer ordinate;
    /** 宽度 */
    @NotNull
    @Min(value = 0, message = "组件宽度不能小于0")
    @Max(value = 12, message = "组件宽度不能大于12")
    private Integer width;
    /** 高度 */
    @NotNull
    @Min(value = 0, message = "组件高度不能小于0")
    private Integer high;

}
