package com.self.cloud.demo.redis;

import io.reactivex.Single;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import reactor.core.publisher.Mono;

/**
 * @author: liruichuan
 * @Date: 2019/12/13 14:26
 * @Description: redission 使用
 */
public class RedissonUtils {

    /**
     * 配置
     * @return
     */
    public static Config getConfig(){
        Config config = new Config();
        config.setTransportMode(TransportMode.EPOLL);
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        return config;
    }

    /**
     * 获取客户端类
     * @return
     */
    public static RedissonClient getClient(){
        return Redisson.create(getConfig());
    }

    public static void main(String[] args) {
        RedissonClient client = getClient();

        RAtomicLong atomicLong = client.getAtomicLong("demo_long");
        //同步执行方式 设置值
        atomicLong.compareAndSet(2,100);
        //异步执行方式
        RFuture<Boolean> result = atomicLong.compareAndSetAsync(2,100);
        result.whenComplete((resp,exception)->{
            //执行成功
            if(resp){

            }else{
                //失败 处理异常 打印异常信息
                System.out.println(exception.getMessage());
            }
        });


        RedissonReactiveClient reactiveClient = Redisson.createReactive(getConfig());
        RAtomicLongReactive reactiveLong = reactiveClient.getAtomicLong("demo_reactive_long");
        // 异步流执行方式
        Mono<Boolean> reactiveResult = reactiveLong.compareAndSet(3, 401);

        RedissonRxClient rxClient = Redisson.createRx(getConfig());
        RAtomicLongRx longObject= rxClient.getAtomicLong("demo_rx_long");
        // RxJava2方式
        Single<Boolean> rxResult = longObject.compareAndSet(3, 401);


    }
}
