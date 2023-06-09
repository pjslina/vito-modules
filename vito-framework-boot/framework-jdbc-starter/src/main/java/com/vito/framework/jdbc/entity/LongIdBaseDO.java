package com.vito.framework.jdbc.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author panjin
 */
@MappedSuperclass
public class LongIdBaseDO extends BaseDO {

    private static final long serialVersionUID = 2868510737784428964L;
    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LongIdBaseDO{" +
                "id=" + id +
                '}';
    }

}
