package com.vito.example.grpc.user;

import com.vito.bank.lib.User;
import com.vito.framework.common.utils.MapStructOfGrpcUtil;
import com.vito.framework.common.utils.MapStructUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author panjin
 */
@Mapper(uses = {MapStructUtil.class, MapStructOfGrpcUtil.class})
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    /**
     * 将protobuf对象转换成数据库实体
     * @param user
     * @return
     */
    @Mapping(target = "createTime", qualifiedByName = "longToLdt")
    @Mapping(target = "updateTime", qualifiedByName = "longToLdt")
    @Mapping(target = "id", source = "id", conditionExpression = "java(user.getId() != 0L)")
    UserDO protobufOfUserToDataObject(User user);

    /**
     * 将数据库实体转换成protobuf的user对象
     * @param userDO
     * @return
     */
    @Mapping(target = "createTime", qualifiedByName = "ldtToLongByMilli", conditionExpression = "java(userDO.getCreateTime() != null)")
    @Mapping(target = "updateTime", qualifiedByName = "ldtToLongByMilli", conditionExpression = "java(userDO.getUpdateTime() != null)")
    User dataObjectToProtobufOfUser(UserDO userDO);

    /**
     * 将数据库实体集合转换成protobuf的user对象集合
     * @param users
     * @return
     */
    List<User> userDoListTouserProtoList(List<UserDO> users);

}
