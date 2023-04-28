package com.vito.example.grpc.user;

import com.vito.bank.lib.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author panjin
 */
@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    /**
     * protobuf to dto
     * @param user
     * @return
     */
    UserCO protobufToDtoOfUser(User user);

    /**
     * protobuf to dto of list of users
     * @param users
     * @return
     */
    List<UserCO> protobufToDtoOfUsers(List<User> users);

    /**
     * cmd to dto of user
     * @param user
     * @return
     */
    User cmdToProtobufOfUser(UserCmd user);
}
