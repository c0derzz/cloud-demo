package com.self.cloud.demo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Auther: LiRuiChuan
 * @Date: 2019/11/16 21:30
 * @Description:
 */
public class RedisTest extends BaseJunitTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void setTest(){

        redisTemplate.opsForValue().set("demo:1","user:1");

        System.out.println(redisTemplate.opsForValue().get("demo:1"));

    }

}
