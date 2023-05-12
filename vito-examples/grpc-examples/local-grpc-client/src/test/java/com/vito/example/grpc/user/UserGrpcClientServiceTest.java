package com.vito.example.grpc.user;

import com.vito.bank.lib.CreateUserRequest;
import com.vito.bank.lib.User;
import com.vito.bank.lib.UserServiceGrpc;
import com.vito.framework.dto.SingleResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class UserGrpcClientServiceTest {

    private UserGrpcClientService userGrpcClientService = new UserGrpcClientService();
    // 注意这个名字必须是userStub，因为在UserGrpcClientService中有这个名字的属性
    private UserServiceGrpc.UserServiceBlockingStub userStub;

    @BeforeEach
    public void before() {
        userStub = Mockito.mock(UserServiceGrpc.UserServiceBlockingStub.class);
        ReflectionTestUtils.setField(userGrpcClientService, "userStub", userStub);
    }

    @Test
    public void testCreateUser() {
        UserCmd cmd = new UserCmd();
        cmd.setUserName("u1");
        cmd.setUserPassword("p2");
        cmd.setIdCard("c3");
        cmd.setPhone("15208533853");
        cmd.setAddress("a4");
        long createTime = System.currentTimeMillis();
        Mockito.when(userStub.createUser(Mockito.any(CreateUserRequest.class))).thenAnswer(new Answer<User>() {
            @Override
            public User answer(org.mockito.invocation.InvocationOnMock invocationOnMock) throws Throwable {
                CreateUserRequest createUserRequest = invocationOnMock.getArgument(0);
                return User.newBuilder().setUserName(createUserRequest.getUser().getUserName())
                        .setUserPassword(createUserRequest.getUser().getUserPassword())
                        .setIdCard(createUserRequest.getUser().getIdCard())
                        .setPhone(createUserRequest.getUser().getPhone())
                        .setAddress(createUserRequest.getUser().getAddress())
                        .setCreateTime(createTime).build();
            }
        });
        SingleResponse user = userGrpcClientService.createUser(cmd);
        assertNotNull(user.getData());
        UserCO userData = (UserCO) user.getData();
        assertEquals(cmd.getUserName(), userData.getUserName());
        assertEquals(cmd.getUserPassword(), userData.getUserPassword());
        assertEquals(cmd.getIdCard(), userData.getIdCard());
        assertEquals(cmd.getPhone(), userData.getPhone());
        assertEquals(cmd.getAddress(), userData.getAddress());
        assertEquals(createTime, userData.getCreateTime());
    }
}
