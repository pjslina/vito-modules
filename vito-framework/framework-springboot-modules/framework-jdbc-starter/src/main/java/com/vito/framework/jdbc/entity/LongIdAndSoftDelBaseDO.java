package com.vito.framework.jdbc.entity;

import javax.persistence.*;

/**
 * @author panjin
 */
@MappedSuperclass
public class LongIdAndSoftDelBaseDO extends BaseDO {

    @Id
    @GeneratedValue
    private Long id;

    /** 是否删除0-未删除，1-删除 */
    @Column(columnDefinition="TINYINT UNSIGNED NOT NULL DEFAULT 0")
    private Integer isDeleted = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "LongIdAndSoftDelBaseDO{" +
                "id=" + id +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
