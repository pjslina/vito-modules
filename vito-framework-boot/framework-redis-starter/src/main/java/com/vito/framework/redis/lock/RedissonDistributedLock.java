package com.vito.framework.redis.lock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author panjin
 */
@ConditionalOnClass(RedissonClient.class)
@Slf4j
public class RedissonDistributedLock implements DistributedLock {

    @Autowired
    private RedissonClient redisson;
    private final ThreadLocal<Map<String, Integer>> lockers = ThreadLocal.withInitial(HashMap::new);

    private DistributedLockWrapper getLock(String key, boolean isFair) {
        RLock lock;
        if (isFair) {
            lock = redisson.getFairLock(key);
        } else {
            lock = redisson.getLock(key);
        }
        return new DistributedLockWrapper(lock, this);
    }

    @Override
    public DistributedLockWrapper lock(String key, long leaseTime, TimeUnit unit, boolean isFair) {
        Result result = getResult(key, isFair);
        if (result == null) {
            return null;
        }
        result.lock.lock(leaseTime, unit);
        result.locker.put(key, 1);
        return result.distributedLockWrapper;
    }

    @Override
    public DistributedLockWrapper tryLock(String key, long waitTime, long leaseTime, TimeUnit unit, boolean isFair) throws InterruptedException {
        Result result = getResult(key, isFair);
        if (result == null) {
            return null;
        }
        if (result.lock().tryLock(waitTime, leaseTime, unit)) {
            result.locker().put(key, 1);
            return result.distributedLockWrapper();
        }
        return null;
    }

    private Result getResult(String key, boolean isFair) {
        Map<String, Integer> locker = lockers.get();
        // 重入计数
        int count = locker.getOrDefault(key, 0);
        if(count > 0) {
            locker.put(key, count + 1);
            log.info("锁[{}]重入{}次", key, count + 1);
            return null;
        }
        DistributedLockWrapper distributedLockWrapper = getLock(key, isFair);
        RLock lock = (RLock)distributedLockWrapper.getLock();
        return new Result(locker, distributedLockWrapper, lock);
    }

    private record Result(Map<String, Integer> locker, DistributedLockWrapper distributedLockWrapper, RLock lock) {
    }

    @Override
    public void unlock(Object lock) {
        if(lock == null) {
            log.error("锁对象为空,解锁失败");
            return;
        }
        try {
            if(lock instanceof RLock rlock) {
                Map<String, Integer> locker = lockers.get();
                int count = locker.getOrDefault(rlock.getName(), 1);
                if (count > 1) {
                    locker.put(rlock.getName(), count - 1);
                } else {
                    locker.remove(rlock.getName());
                    if (rlock.isLocked()) {
                        rlock.unlock();
                    }
                }
            }
        } catch (Exception e) {
            log.error("释放锁失败", e);
        }
    }
}
