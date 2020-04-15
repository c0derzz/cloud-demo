package com.self.cloud.demo.redis;

/**
 * @author: liruichuan
 * @Date: 2019/12/13 11:18
 * @Description: redis 锁常量
 */
public class RedisLockConstants {

    public static final String OK = "OK";

    /**
     * NX|XX, NX -- Only set the key if it does not already exist.
     * XX -- Only set the key if it already exist.
     */
    public static final String NOT_EXIST = "NX";

    public static final String EXIST = "XX";

    /**
     * expx EX|PX, expire time units: EX = seconds; PX = milliseconds
     */
    public static final String SECONDS = "EX";
    public static final String MILLISECONDS = "PX";

    private RedisLockConstants() {}
}
