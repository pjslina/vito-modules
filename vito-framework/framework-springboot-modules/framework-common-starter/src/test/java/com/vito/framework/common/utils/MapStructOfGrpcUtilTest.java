package com.vito.framework.common.utils;

import com.google.protobuf.Timestamp;
import com.vito.framework.exception.BizException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;

/**
 * @TestInstance(TestInstance.Lifecycle.PER_CLASS)是解决
 * BeforeAll或者AfterAll注解在非静态方法报JUnitException异常的问题
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MapStructOfGrpcUtilTest {

    private MapStructOfGrpcUtil mapStructOfGrpcUtil;

    @BeforeAll
    public void setUp() {
        mapStructOfGrpcUtil = new MapStructOfGrpcUtil();
    }

    @Test
    public void testStrToProtoTs() {
        Timestamp timestamp = mapStructOfGrpcUtil.strToProtoTs("2023-05-10 10:24:24", "yyyy-MM-dd HH:mm:ss");
        Assertions.assertEquals(1683714264L, timestamp.getSeconds());
    }

    @Test
    public void testProtoTsToLdt() {
        LocalDateTime dateTime = mapStructOfGrpcUtil.protoTsToLdt(Timestamp.newBuilder().setSeconds(System.currentTimeMillis() / 1000).build());
        Assertions.assertNotNull(dateTime);
    }

    @Test
    public void testLdtToProtoTs() {
        Timestamp timestamp = mapStructOfGrpcUtil.ldtToProtoTs(LocalDateTime.now());
        Assertions.assertNotNull(timestamp);
    }

    @Test
    public void testProtoTsToLong() {
        Long timestamp = mapStructOfGrpcUtil.protoTsToLong(Timestamp.newBuilder().setSeconds(System.currentTimeMillis() / 1000L)
                .setNanos((int) (System.currentTimeMillis() / 1000000L)).build());
        Assertions.assertNotNull(timestamp);
    }

    @Test
    public void testProtoTsToLongOfSecond() {
        Long timestamp = mapStructOfGrpcUtil.protoTsToLong(Timestamp.newBuilder().setSeconds(System.currentTimeMillis() / 1000L).build());
        Assertions.assertNotNull(timestamp);
    }

    @Test
    public void testLongToProtoTs() {
        Timestamp timestamp = mapStructOfGrpcUtil.longToProtoTs(System.currentTimeMillis());
        Assertions.assertNotNull(timestamp);
    }

    @Test
    public void testLongToProtoTsBySecond() {
        Assertions.assertThrowsExactly(BizException.class, () -> {
            mapStructOfGrpcUtil.longToProtoTs(System.currentTimeMillis() / 1000L);
        });
    }
}
