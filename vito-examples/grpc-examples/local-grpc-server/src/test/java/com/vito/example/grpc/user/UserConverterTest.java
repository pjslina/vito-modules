package com.vito.example.grpc.user;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.vito.bank.lib.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserConverterTest {

    @Test
    public void testProtobufOfUserToDataObject() {
        long createTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) * 1000;
        User user = User.newBuilder().setUserName("u1").setUserPassword("p2").setIdCard("c3")
                .setPhone("15208533853").setAddress("a4")
                .setCreateTime(createTime).build();
        UserDO userDO = UserConverter.INSTANCE.protobufOfUserToDataObject(user);
        assertEquals("u1", userDO.getUserName());
        assertEquals(LocalDateTimeUtil.of(createTime), userDO.getCreateTime());
        assertEquals("p2", userDO.getUserPassword());
    }

}
