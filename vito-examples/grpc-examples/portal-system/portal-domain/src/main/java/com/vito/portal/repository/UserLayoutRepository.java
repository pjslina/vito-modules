package com.vito.portal.repository;

import com.vito.portal.domain.entity.UserLayout;

import java.util.List;

/**
 * @author panjin
 */
public interface UserLayoutRepository {

    /**
     * 保存用户布局
     * @param userLayout
     */
    void save(UserLayout userLayout);

    /**
     * 批量保存用户布局
     * @param userLayoutList
     */
    void saveOrUpdateBatch(List<UserLayout> userLayoutList);
}
