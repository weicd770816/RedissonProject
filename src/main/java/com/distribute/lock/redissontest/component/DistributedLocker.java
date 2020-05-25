package com.distribute.lock.redissontest.component;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * @author wcd
 * @create 2020/5/23-13:13
 */
public interface DistributedLocker {
    RLock lock(String lockKey);
    RLock lock(String lockKey, int timeout);
    RLock lock(String lockKey, TimeUnit unit, int timeout);
    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime);
    void unlock(String lockKey);
    void unlock(RLock lock);
}
