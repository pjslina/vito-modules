package com.vito.example.grpc.user;

import com.google.protobuf.Empty;
import com.vito.bank.lib.*;
import com.vito.example.grpc.common.ErrorCodeEnum;
import com.vito.framework.exception.Assert;
import com.vito.framework.redis.template.RedisRepository;
import com.vito.grpc.base.LongIdRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author panjin
 */
@GrpcService
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisRepository redisRepository;

    private static final String USER_KEY = "vito:user:";

    @Override
    public void listUsers(ListUsersRequest request, StreamObserver<ListUsersResponse> responseObserver) {
        Pageable pageable = PageRequest.of(0, request.getPageSize());
        Page<UserDO> userDOPage = userRepository.findByIdGreaterThanOrderByIdAsc(request.getPageToken(), pageable);
        ListUsersResponse.Builder builder = ListUsersResponse.newBuilder()
                .addAllUsers(UserConverter.INSTANCE.userDoListTouserProtoList(userDOPage.getContent()))
                .setTotalSize(userDOPage.getTotalElements());
        if (userDOPage.getTotalElements() > 0) {
            builder.setNextPageToken(userDOPage.getContent().get(userDOPage.getContent().size() - 1).getId());
        }
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getUser(LongIdRequest request, StreamObserver<User> responseObserver) {
        UserDO userDOCache = (UserDO) redisRepository.get(USER_KEY + request.getId());
        if (userDOCache == null) {
            Optional<UserDO> userDO = userRepository.findById(request.getId());
            Assert.isTrue(userDO.isPresent(), ErrorCodeEnum.RES_IS_NULL);
            redisRepository.setExpire(USER_KEY + request.getId(), userDO.get(),60L);
            responseObserver.onNext(UserConverter.INSTANCE.dataObjectToProtobufOfUser(userDO.get()));
            responseObserver.onCompleted();
            return;
        }
        responseObserver.onNext(UserConverter.INSTANCE.dataObjectToProtobufOfUser(userDOCache));
        responseObserver.onCompleted();
    }

    @Override
    public void createUser(CreateUserRequest request, StreamObserver<User> responseObserver) {
        UserDO userDO = UserConverter.INSTANCE.protobufOfUserToDataObject(request.getUser());
        UserDO save = userRepository.save(userDO);
        responseObserver.onNext(UserConverter.INSTANCE.dataObjectToProtobufOfUser(save));
        responseObserver.onCompleted();
    }

    @Override
    public void updateUser(UpdateUserRequest request, StreamObserver<User> responseObserver) {
        UserDO userDO = UserConverter.INSTANCE.protobufOfUserToDataObject(request.getUser());
        UserDO save = userRepository.save(userDO);
        redisRepository.setExpire(USER_KEY + request.getUser().getId(), save,60L);
        responseObserver.onNext(UserConverter.INSTANCE.dataObjectToProtobufOfUser(save));
        responseObserver.onCompleted();
    }

    @Override
    public void deleteUser(LongIdRequest request, StreamObserver<Empty> responseObserver) {
        userRepository.deleteById(request.getId());
        redisRepository.del(USER_KEY + request.getId());
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void loginUser(LoginUserRequest request, StreamObserver<Empty> responseObserver) {
        UserDO userDO = new UserDO();
        userDO.setUserName(request.getUserName());
        userDO.setUserPassword(request.getUserPassword());
        Example<UserDO> example = Example.of(userDO);
        Optional<UserDO> userDB = userRepository.findOne(example);
        Assert.isTrue(userDB.isPresent(), ErrorCodeEnum.RES_IS_NULL);
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }
}
