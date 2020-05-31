package com.distribute.lock.redissontest.util;

import com.distribute.lock.redissontest.component.DistributedLocker;
import com.distribute.lock.redissontest.component.RedisDistributedLocker;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RLock;
import org.redisson.api.RSemaphore;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author wcd
 * @create 2020/5/24-13:56
 */
@Component
public class RedisLockUtil {
    @Resource
    private RedisDistributedLocker distributedLocker;

    /**
     * 加锁
     * @param lockKey
     * @return
     */
    public  RLock lock(String lockKey){
        return distributedLocker.lock(lockKey);
    }

    /**
     * 释放锁
     * @param lock
     */
    public  void unlock(RLock lock){
        distributedLocker.unlock(lock);
    }

    /**
     * 释放锁
     * @param lockKey
     */
    public  void unlock(String lockKey){
        distributedLocker.unlock(lockKey);
    }

    /**
     * 带超时的锁
     * @param lockKey
     * @param timeout 超时时间   单位：秒
     */
    public  RLock lock(String lockKey, int timeout) {
        return distributedLocker.lock(lockKey, timeout);
    }
    /**
     * 带超时的锁
     * @param lockKey
     * @param unit 时间单位
     * @param timeout 超时时间
     */
    public  RLock lock(String lockKey, int timeout,TimeUnit unit ) {
        return distributedLocker.lock(lockKey, unit, timeout);
    }
    /**
     * 尝试获取锁
     * @param lockKey
     * @param waitTime 最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    public  boolean tryLock(String lockKey, int waitTime, int leaseTime) {
        return distributedLocker.tryLock(lockKey, TimeUnit.SECONDS, waitTime, leaseTime);
    }
    /**
     * 尝试获取锁
     * @param lockKey
     * @param unit 时间单位
     * @param waitTime 最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    public  boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
        return distributedLocker.tryLock(lockKey, unit, waitTime, leaseTime);
    }
    /**
     * 获取计数器
     *
     * @param name
     * @return
     */
    public  RCountDownLatch getCountDownLatch(String name){
        return distributedLocker.getCountDownLatch(name);
    }
    /**
     * 获取信号量
     *
     * @param name
     * @return
     */
    public  RSemaphore getSemaphore(String name){
        return distributedLocker.getSemaphore(name);
    }

}
