package com.vito.framework.s3.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author panjin
 */
@Getter
@Setter
public class ObjectInfo {

    /**
     * 对象查看路径
     */
    private String objectUrl;
    /**
     * 对象保存路径
     */
    private String objectPath;
}
