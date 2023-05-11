package com.vito.framework.jdbc.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 *
 * @author panjin
 */
@MappedSuperclass
public class BaseDO implements java.io.Serializable {

    private static final long serialVersionUID = -1768509121292012269L;
    /** */
    @Column(nullable = false)
    private Long createBy = 0L;
    /** Column注解只在创建表的时候使用，如果表不是自动生成的，需要通过建表SQL去解决 */
    @Column(columnDefinition = "DATETIME NOT NULL")
    private LocalDateTime createTime;
    /** */
    @Column(nullable = false)
    private Long updateBy = 0L;
    /** */
    @Column(columnDefinition = "DATETIME NOT NULL")
    private LocalDateTime updateTime;

    @PrePersist
    public void prePersist() {
        if (createTime == null) {
            createTime = LocalDateTime.now();
        }
        if (updateTime == null) {
            updateTime = LocalDateTime.now();
        }
    }

    /**
     * 更新之前设置更新时间
     */
    @PreUpdate
    public void preUpdate() {
        updateTime = LocalDateTime.now();
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "BaseDO{" +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                '}';
    }
}
