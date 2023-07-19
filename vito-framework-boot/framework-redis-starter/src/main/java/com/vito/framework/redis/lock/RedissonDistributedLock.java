package com.vito.framework.redis.lock;

import com.vito.framework.exception.SysErrorCodeEnum;
import com.vito.framework.exception.SysException;
import com.vito.framework.util.CommonConstant;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.util.concurrent.TimeUnit;

/**
 * @author panjin
 */
@ConditionalOnClass(RedissonClient.class)
public class RedissonDistributedLock implements DistributedLock {

    @Autowired
    private RedissonClient redisson;

    private DistributedLockWrapper getLock(String key, boolean isFair) {
        RLock lock;
        if (isFair) {
            lock = redisson.getFairLock(CommonConstant.LOCK_KEY_PREFIX + ":" + key);
        } else {
            lock = redisson.getLock(CommonConstant.LOCK_KEY_PREFIX + ":" + key);
        }
        return new DistributedLockWrapper(lock, this);
    }

    @Override
    public DistributedLockWrapper lock(String key, long leaseTime, TimeUnit unit, boolean isFair) {
        DistributedLockWrapper distributedLockWrapper = getLock(key, isFair);
        RLock lock = (RLock)distributedLockWrapper.getLock();
        lock.lock(leaseTime, unit);
        return distributedLockWrapper;
    }

    @Override
    public DistributedLockWrapper tryLock(String key, long waitTime, long leaseTime, TimeUnit unit, boolean isFair) throws InterruptedException {
        DistributedLockWrapper distributedLockWrapper = getLock(key, isFair);
        RLock lock = (RLock)distributedLockWrapper.getLock();
        if (lock.tryLock(waitTime, leaseTime, unit)) {
            return distributedLockWrapper;
        }
        return null;
    }

    @Override
    public void unlock(Object lock) {
        if(lock == null) {
            throw new SysException(SysErrorCodeEnum.LOCKER_IS_NULL);
        }
        try {
            if(lock instanceof RLock rlock) {
                if(rlock.isLocked()) {
                    rlock.unlock();
                }
            } else {
                throw new SysException(SysErrorCodeEnum.GET_LOCK_ERROR);
            }
        } finally {
            // RLock的unlock方法本身处理了重复释放的问题，所以这里不需要再判断是否重复释放
            if(lock instanceof RLock rlock) {
                rlock.unlock();
            }
        }
    }
}
