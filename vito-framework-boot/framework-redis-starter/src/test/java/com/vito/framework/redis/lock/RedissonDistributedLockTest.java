package com.vito.framework.redis.lock;

import com.vito.framework.redis.TestApplication;
import com.vito.framework.redis.config.TestRedisConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {TestApplication.class, TestRedisConfiguration.class})
class RedissonDistributedLockTest {

    @Autowired
    private BusinessService businessService;

    @Test
    void testBiz() {
        businessService.biz();
    }

    @Test
    void testBiz01() {
        businessService.biz01();
    }

    @Test
    void testBiz02() {
        businessService.biz02();
    }

    @Test
    void testBiz03() {
        businessService.biz03();
    }

    @Test
    void testPlaceOrder() {
        businessService.placeOrder("123456");
    }
}
