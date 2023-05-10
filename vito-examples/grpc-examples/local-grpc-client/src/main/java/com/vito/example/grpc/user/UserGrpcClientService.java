package com.vito.example.grpc.user;

import com.vito.bank.lib.*;
import com.vito.framework.dto.MobilePageResponse;
import com.vito.framework.dto.Response;
import com.vito.framework.dto.SingleResponse;
import com.vito.grpc.base.LongIdRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author panjin
 */
@Service
public class UserGrpcClientService {

    @GrpcClient("local-grpc-server")
    private UserServiceGrpc.UserServiceBlockingStub userStub;

    public MobilePageResponse<UserCO> listUsers(Integer pageSize, String pageToken) {
        ListUsersResponse response = userStub.listUsers(ListUsersRequest.newBuilder()
                .setPageSize(pageSize).setPageToken(Long.valueOf(pageToken)).setViewValue(1).build());
        List<User> users = response.getUsersList();
        return MobilePageResponse.of(UserConverter.INSTANCE.protobufToDtoOfUsers(users), (int)response.getTotalSize(), pageSize, String.valueOf(response.getNextPageToken()));
    }

    public SingleResponse getUser(String userId) {
        User user = userStub.getUser(LongIdRequest.newBuilder().setId(Long.valueOf(userId)).build());
        return SingleResponse.of(UserConverter.INSTANCE.protobufToDtoOfUser(user));
    }

    public SingleResponse createUser(UserCmd user) {
        User createUser = userStub.createUser(CreateUserRequest.newBuilder().setUser(UserConverter.INSTANCE.cmdToProtobufOfUser(user)).build());
        return SingleResponse.of(UserConverter.INSTANCE.protobufToDtoOfUser(createUser));
    }

    public SingleResponse updateUser(String userId, UserCmd user) {
        User updateUser = userStub.updateUser(UpdateUserRequest.newBuilder().setUser(UserConverter.INSTANCE.cmdToProtobufOfUser(user)).build());
        return SingleResponse.of(UserConverter.INSTANCE.protobufToDtoOfUser(updateUser));
    }

    public Response deleteUser(String userId) {
        userStub.deleteUser(LongIdRequest.newBuilder().setId(Long.valueOf(userId)).build());
        return Response.buildSuccess();
    }
}
