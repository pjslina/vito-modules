package com.vito.example.grpc.user;

import com.vito.framework.dto.MobilePageResponse;
import com.vito.framework.dto.Response;
import com.vito.framework.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author panjin
 */
@RestController
public class UserController {

    @Autowired
    private UserGrpcClientService userGrpcClientService;

    @GetMapping("/users")
    public MobilePageResponse<UserCO> getListUsers(@RequestParam Integer pageSize, @RequestParam String pageToken) {
        return userGrpcClientService.listUsers(pageSize, pageToken);
    }

    @GetMapping("/users/{userId}")
    public SingleResponse getUser(@PathVariable String userId) {
        return userGrpcClientService.getUser(userId);
    }

    @PostMapping("/users")
    public SingleResponse createUser(@RequestBody UserCmd user) {
        return userGrpcClientService.createUser(user);
    }

    @PutMapping("/users/{userId}")
    public SingleResponse updateUser(@PathVariable String userId, @RequestBody UserCmd user) {
        return userGrpcClientService.updateUser(userId, user);
    }

    @DeleteMapping("/users/{userId}")
    public Response deleteUser(@PathVariable String userId) {
        return userGrpcClientService.deleteUser(userId);
    }
}
