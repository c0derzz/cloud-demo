package com.self.cloud.demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

/**
 * @author: liruichuan
 * @Date: 2019/12/12 15:22
 * @Description: redis client
 */
public class RedisClient {

    private static final String HOST = "127.0.0.1";
    private static final Integer PORT = 6379;

    public static void main(String[] args) {
        JedisPool jedisPool = getJedisPool(HOST,PORT);
        /*long start = System.currentTimeMillis();
        testHset(jedisPool.getResource());
        long end = System.currentTimeMillis();
        System.out.println("testHset耗时：" + (end-start)/1000 + "s");*/

        long start = System.currentTimeMillis();
        testPipeline(jedisPool.getResource());
        long end = System.currentTimeMillis();
        System.out.println("testPipeline耗时：" + (end-start)/1000.00 + "s");

    }

    /**
     * 没有使用pipieline的情况下 会用mset
     */
    public static void testHset(Jedis jedis) {

        for(int i = 1 ; i <= 10000 ; i++ ) {
            jedis.hset("hashKey-" + i , "field-" + i , "value-" + i);
        }
    }

    /**
     *  使用pipeline的情况下
     */
    public static void testPipeline(Jedis jedis) {
        Pipeline pipeline = jedis.pipelined();
        for(int i = 0 ; i < 10000 ; i++ ) {
            pipeline.set("key-"+i,"value-"+i);
        }
        pipeline.sync();
    }

    private static JedisPool getJedisPool(String host,Integer port){
        if(host == null || port == null){
            return null;
        }
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(100);
        poolConfig.setTestOnBorrow(false);
        poolConfig.setTestOnReturn(false);

        JedisPool jedisPool = new JedisPool(poolConfig, host, port);
        return jedisPool;

    }
}
