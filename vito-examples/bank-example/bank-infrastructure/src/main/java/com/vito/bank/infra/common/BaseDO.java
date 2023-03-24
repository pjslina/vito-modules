package com.vito.bank.infra.common;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * MappedSuperclass注解是解决jpa的实体类在orm创建表结构时，不会创建继承类的这些字段问题
 * @author panjin
 */
@MappedSuperclass
public class BaseDO {

    /** 是否删除0-未删除，1-删除 */
    private Integer isDeleted;
    /** */
    private Long createBy;
    /** */
    private LocalDateTime createTime;
    /** */
    private Long updateBy;
    /** */
    private LocalDateTime updateTime;

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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
                "isDeleted=" + isDeleted +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                '}';
    }
}
