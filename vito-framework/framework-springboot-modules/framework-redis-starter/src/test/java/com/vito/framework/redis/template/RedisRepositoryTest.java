package com.vito.framework.redis.template;

import com.vito.framework.redis.TestApplication;
import com.vito.framework.redis.config.TestRedisConfiguration;
import com.vito.framework.redis.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = {TestApplication.class, TestRedisConfiguration.class})
public class RedisRepositoryTest {

    @Autowired
    private RedisRepository redisRepository;

    @Test
    public void testSet() {
        redisRepository.set("test", "test");
        Assertions.assertEquals("test", redisRepository.get("test"));
    }

    @Test
    public void testSetObject() {
        Person person = new Person();
        person.setName("张三");
        person.setAge(18);
        person.setAddress("北京市");
        person.setBirthday(LocalDateTime.now());
        redisRepository.set("person", person);
        Assertions.assertEquals(person, redisRepository.get("person"));
    }

    @Test
    public void testSetList() {
        List<Person> list = new ArrayList<>();
        Person person = new Person();
        person.setName("张三");
        person.setAge(18);
        person.setAddress("北京市");
        person.setBirthday(LocalDateTime.now());
        list.add(person);
        redisRepository.set("person2", list);
        Assertions.assertEquals(list, redisRepository.get("person2"));
    }

    @Test
    public void testKeys() {
        redisRepository.set("test998", "test");
        redisRepository.set("test997", "test1");
        Assertions.assertEquals(1, redisRepository.keys("*97").size());
    }

    @Test
    public void testPutHashValue() {
        redisRepository.putHashValue("test4", "test", "test");
        Person person = new Person();
        person.setName("张三");
        person.setAge(18);
        person.setAddress("北京市");
        person.setBirthday(LocalDateTime.now());
        redisRepository.putHashValue("test4", "person", person);
        Assertions.assertEquals("test", redisRepository.getHashValues("test4", "test"));
        Assertions.assertEquals(person, redisRepository.getHashValues("test4", "person"));
    }

    @Test
    public void testDbSize() {
        redisRepository.flushDB();
        Assertions.assertEquals(0L, redisRepository.dbSize());
        redisRepository.set("test5", "test");
        Assertions.assertEquals(1L, redisRepository.dbSize());
    }

    @Test
    public void testFlushDB() {
        redisRepository.set("test6", "test");
        redisRepository.flushDB();
        Assertions.assertEquals(0L, redisRepository.dbSize());
    }

    @Test
    public void testExists() {
        redisRepository.set("test7", "test");
        Assertions.assertTrue(redisRepository.exists("test7"));
    }

    @Test
    public void testDel() {
        redisRepository.set("test8", "test");
        redisRepository.del("test8");
        Assertions.assertFalse(redisRepository.exists("test8"));
    }

    @Test
    public void testIncr() {
        redisRepository.set("test9", 1);
        redisRepository.incr("test9");
        Assertions.assertEquals(2, redisRepository.get("test9"));
    }

    @Test
    public void testLeftPush() {
        redisRepository.leftPush("test10", "test");
        redisRepository.leftPush("test10", "test1");
        Assertions.assertEquals("test1", redisRepository.leftPop("test10"));
    }

    @Test
    public void testLeftPushAll() {
        redisRepository.leftPushAll("test11", Arrays.asList("test", "test1"));
        Assertions.assertEquals(1L, redisRepository.length("test11"));
        Assertions.assertEquals(Arrays.asList("test", "test1"), redisRepository.leftPop("test11"));
    }

    @Test
    public void testLeftPushAllOfObjects() {
        redisRepository.leftPushAll("test12", "value", Arrays.asList("test", "test1", "test2"));
        Assertions.assertEquals(2L, redisRepository.length("test12"));
        Assertions.assertEquals(Arrays.asList("test", "test1", "test2"), redisRepository.leftPop("test12"));
    }

    @Test
    public void testRightPush() {
        redisRepository.rightPush("test13", "test");
        redisRepository.rightPush("test13", "test1");
        Assertions.assertEquals("test1", redisRepository.rightPop("test13"));
        Assertions.assertEquals(1, redisRepository.length("test13"));
    }

    @Test
    public void testLengthOfKeyNotExists() {
        Assertions.assertEquals(0, redisRepository.length("test14"));
    }

    @Test
    public void testLengthOfException() {
        redisRepository.set("test15", "test");
        Assertions.assertThrowsExactly(InvalidDataAccessApiUsageException.class, () -> {
            redisRepository.length("test15");
        });
    }

    @Test
    public void testRemove() {
        redisRepository.rightPush("test16", "a");
        redisRepository.rightPush("test16", "b");
        redisRepository.rightPush("test16", "c");
        redisRepository.rightPush("test16", "b");
        redisRepository.rightPush("test16", "d");
        redisRepository.rightPush("test16", "b");
        // 输出列表中所有元素
        Assertions.assertEquals(Arrays.asList("a", "b", "c", "b", "d", "b"), redisRepository.getList("test16", 0, -1));
        // 移除列表中第一个值为 "b" 的元素
        Assertions.assertEquals(1, redisRepository.remove("test16", 1, "b"));
        Assertions.assertEquals(Arrays.asList("a", "c", "b", "d", "b"), redisRepository.getList("test16", 0, -1));
        // 移除列表中最后两个值为 "b" 的元素
        Assertions.assertEquals(2, redisRepository.remove("test16", 2, "b"));
        Assertions.assertEquals(Arrays.asList("a", "c", "d"), redisRepository.getList("test16", 0, -1));
    }
}
