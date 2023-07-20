package com.vito.framework.redis.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class BusinessService {

    @Autowired
    private DistributedLock distributedLock;

    public void biz() {
        DistributedLockWrapper lock = null;
        try {
            //获取锁
            lock = distributedLock.lock("order_key");
            //业务逻辑
            log.info("获取分布式锁成功, 执行业务逻辑");
        } catch (Exception e) {
            log.error("获取分布式锁失败", e);
        } finally {
            //释放锁
            if (lock != null) {
                try {
                    lock.close();
                } catch (Exception e) {
                    log.error("释放分布式锁失败", e);
                }
            }
        }
    }

    /**
     * 推荐使用方式
     * 使用try-with-resources语法，自动释放锁
     * 业务代码执行完毕后，自动调用DistributedLock的close方法，释放锁
     * 如果业务代码异常，也会自动释放锁
     */
    public void biz01() {
        String key = "order_key";
        try (DistributedLockWrapper lock = distributedLock.lock(key)) {
            log.info("获取分布式锁成功");
        } catch (Exception e) {
            log.error("获取分布式锁失败", e);
        }
    }

    public void biz02() {
        String key = "order_key";
        // 使用tryLock方法，获取锁失败时，不会阻塞，而是直接返回false
        try (DistributedLockWrapper lock = distributedLock.tryLock(key, 10, TimeUnit.SECONDS)) {
            if (lock == null) {
                // 获取锁失败
                log.warn("获取分布式锁失败");
                return;
            }
            // 业务逻辑
            log.info("获取分布式锁成功");
        } catch (Exception e) {
            log.error("获取分布式锁失败", e);
        }
    }

    /**
     * 注意，如果业务代码执行时间大于锁的过期时间，会导致锁过期，其他线程获取到锁
     * 但是业务代码还没有执行完毕，这时候其他线程就会获取到锁，导致并发问题
     * 解决方案一：不要设置leaseTime参数，使用默认值，在业务最终执行完毕后，主动释放锁
     */
    public void biz03() {
        String key = "order_key";
        try (DistributedLockWrapper lock = distributedLock.lock(key, 5L, TimeUnit.SECONDS)) {
            log.info("获取分布式锁成功");
            //
            Thread.sleep(10000);
            log.info("业务逻辑执行完毕");
        } catch (Exception e) {
            log.error("获取分布式锁失败", e);
        }
    }

    @Lock(key = "'order-'+#orderId")
    public void placeOrder(String orderId) {
        log.info("下单成功，订单号：{}", orderId);
    }

    @Lock(key = "'account-'+#accountId", leaseTime=10)
    public void updateAccount(String accountId) {
        log.info("更新账户成功，账户号：{}", accountId);
    }

    @Lock(key = "'product-'+#productId",waitTime=100, isFair=true)
    public void updateProduct(String productId) {
        log.info("更新商品成功，商品号：{}", productId);
    }
}
