package com.vito.framework.jdbc.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author panjin
 */
@MappedSuperclass
public class SoftDelBaseDO extends BaseDO {

    private static final long serialVersionUID = 3559479738408406417L;
    /** 是否删除0-未删除，1-删除 */
    @Column(columnDefinition="TINYINT NOT NULL DEFAULT 0")
    private Integer isDeleted = 0;

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "SoftDelBaseDO{" +
                "isDeleted=" + isDeleted +
                '}';
    }
}
