package com.vito.example.grpc.user;

import com.vito.bank.lib.CreateUserRequest;
import com.vito.bank.lib.User;
import com.vito.example.grpc.config.TestRedisConfiguration;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TestRedisConfiguration.class}, properties = {"classpath:test/application.yaml"})
public class UserGrpcServiceTest {

    @Autowired
    UserGrpcService userGrpcService;

    @Test
    public void testCreateUser() throws Exception {
        User user = User.newBuilder().setUserName("u1").setUserPassword("p2").setIdCard("c3")
                .setPhone("15208533853").setAddress("a4").build();
        CreateUserRequest request = CreateUserRequest.newBuilder().setUser(user).build();
        StreamRecorder<User> responseObserver = StreamRecorder.create();
        userGrpcService.createUser(request, responseObserver);
        if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
            fail("The call did not terminate in time");
        }
        assertNull(responseObserver.getError());
        List<User> results = responseObserver.getValues();
        assertEquals(1, results.size());
        assertEquals(user.getUserName(), results.get(0).getUserName());
    }
}
