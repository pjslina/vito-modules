package com.vito.portal.repository.impl;

import com.vito.portal.convertor.LayoutConvertor;
import com.vito.portal.domain.entity.UserLayout;
import com.vito.portal.persistence.UserLayoutDbService;
import com.vito.portal.persistence.UserLayoutMapper;
import com.vito.portal.persistence.dataobject.UserLayoutDO;
import com.vito.portal.repository.UserLayoutRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author panjin
 */
@Repository
public class UserLayoutRepositoryImpl implements UserLayoutRepository {

    @Resource
    private UserLayoutMapper userLayoutMapper;
    @Resource
    private UserLayoutDbService userLayoutDbService;

    @Override
    public void save(UserLayout userLayout) {
        UserLayoutDO userLayoutDO = new UserLayoutDO();
        BeanUtils.copyProperties(userLayout, userLayoutDO);
        userLayoutMapper.insert(userLayoutDO);
    }

    @Override
    public void saveOrUpdateBatch(List<UserLayout> userLayoutList) {
        List<UserLayoutDO> userLayoutDOList = LayoutConvertor.INSTANCE.map(userLayoutList);
        userLayoutDbService.saveOrUpdateBatch(userLayoutDOList);
    }
}
