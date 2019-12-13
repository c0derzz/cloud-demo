package com.self.cloud.demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author: liruichuan
 * @Date: 2019/12/13 11:16
 * @Description: redis 分布式锁
 */
public class RedisLock implements Lock{

    private Jedis jedis;

    private String lockKey;

    private String lockValue;

    public RedisLock(Jedis jedis, String lockKey, String lockValue) {
        this.jedis = jedis;
        this.lockKey = lockKey;
        this.lockValue = lockValue;
    }

    public RedisLock(Jedis jedis, String lockKey) {
        this(jedis,lockKey, Thread.currentThread().getId() + UUID.randomUUID().toString());
    }

    @Override
    public void lock() {
        //自旋 阻塞式加锁
        while(true){
            SetParams setParams = new SetParams();
            setParams.nx();
            //设置超时时间  防止线程挂掉之后无法释放锁 导致其他线程无法获取锁
            // 会引出新的问题 超时时间小于线程执行时间 提前释放锁的问题 等到线程执行完之后会将后续获取到锁的线程持有的多给释放掉 可以采取异步刷新
            setParams.ex(30);
            String lockResult = jedis.set(lockKey,lockValue,setParams);
            //加锁成功
            if(RedisLockConstants.OK.equals(lockResult)){
                System.out.println(Thread.currentThread().getId() + " 对应的key:" + lockKey + " 加锁成功");
                break;
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        //释放锁 方法1 可能出现类似于i++ 的问题 线程A设置的值与线程B的值相同 会导致 线程B将线程A锁给释放掉 所以需要使用原子性释放锁的方式
        /*String lockValue = jedis.get(lockKey);
        if (lockValue.equals(lockValue)){
            jedis.del(lockKey);
        }*/

        //使用原子性释放锁 redis 可以执行lua脚本来释放锁
        // 使用lua脚本进行原子删除操作
        String checkAndDelScript = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                                        "return redis.call('del', KEYS[1]) " +
                                    "else " +
                                        "return 0 " +
                                    "end";
        jedis.eval(checkAndDelScript, 1, lockKey, lockValue);



    }

    @Override
    public Condition newCondition() {
        return null;
    }

    /**
     * 线程休眠时间
     * @param sencond
     */
    public void sleepSencends(int sencond){
        try {
            Thread.sleep(sencond*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
