package com.distribute.lock.redissontest.controller;

import com.distribute.lock.redissontest.util.RedisLockUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author wcd
 * @create 2020/5/31-11:47
 */
@RestController
@Slf4j
@RequestMapping("/redisson")
@Api(tags = "redisson",description = "redis分布式锁控制器")
public class RedissonLockController {
    /**
     * 锁测试共享变量
     */
    private Integer lockCount = 10;
    /**
     * 无锁测试共享变量
     */
    private Integer count = 10;
    /**
     * 模拟线程数
     */
    private static int threadNum = 10;
    @Resource
    private RedisLockUtil redisLockUtil;
    /**
     * 模拟并发测试加锁和不加锁
     * @return
     */
    @GetMapping("/test")
    @ApiOperation(value = "模拟并发测试加锁和不加锁")
    public void lock(){
        CountDownLatch countDownLatch=new CountDownLatch(1);
        for (int i=0;i<threadNum;i++){
            new Thread(()->{
                try {
                    countDownLatch.await();
                    //无锁操作
                    testCount();
                    //加锁操作
                    testLockCount();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        countDownLatch.countDown();
    }

    private void testLockCount() {
        String lockKey="lock-test";
        try {
            redisLockUtil.lock(lockKey,2, TimeUnit.SECONDS);//加锁,设置超时时间2秒
            lockCount--;
            log.info("lockCount值："+lockCount);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            redisLockUtil.unlock(lockKey);//释放锁
        }
        log.info("成功释放锁");
    }

    private void testCount() {
        count--;
        log.info("count值："+count);
    }
}
