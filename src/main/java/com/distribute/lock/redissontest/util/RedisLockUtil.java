package com.distribute.lock.redissontest.util;

import com.distribute.lock.redissontest.component.DistributedLocker;

/**
 * @author wcd
 * @create 2020/5/24-13:56
 */
public class RedisLockUtil {
    private static DistributedLocker distributedLocker=SpringContextHolder.getBean("distributedLocker",DistributedLocker.class);

}
