package com.vito.framework.jdbc.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author panjin
 */
@MappedSuperclass
public class LongIdAndSoftDelBaseDO extends BaseDO {

    private static final long serialVersionUID = -6041029622751184317L;
    @Id
    @GeneratedValue
    private Long id;

    /** H2数据库不支持UNSIGNED关键字 */
    /** 是否删除0-未删除，1-删除 */
    @Column(columnDefinition="TINYINT NOT NULL DEFAULT 0")
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
