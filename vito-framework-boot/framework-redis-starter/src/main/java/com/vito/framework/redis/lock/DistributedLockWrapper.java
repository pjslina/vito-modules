package com.vito.framework.redis.lock;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 锁对象抽象，添加了自动释放锁的逻辑
 * 如果使用try-with-resource语法，可以自动释放锁
 * @author panjin
 */
@AllArgsConstructor
public class DistributedLockWrapper implements AutoCloseable {

    @Getter
    private final Object lock;

    private final DistributedLock locker;

    @Override
    public void close() throws Exception {
        locker.unlock(lock);
    }
}
