package com.vito.portal.convertor;

import com.vito.portal.domain.entity.UserLayout;
import com.vito.portal.persistence.dataobject.UserLayoutDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author panjin
 */
@Mapper
public interface LayoutConvertor {

    LayoutConvertor INSTANCE = Mappers.getMapper(LayoutConvertor.class);

    /**
     * 将domain对象转换成data object
     * @param userLayoutList
     * @return
     */
    List<UserLayoutDO> map(List<UserLayout> userLayoutList);
}
