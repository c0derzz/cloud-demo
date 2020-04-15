package com.self.cloud.demo.service;

import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.Collections;

/**
 * @Auther: LiRuiChuan
 * @Date: 2020/3/16 15:53
 * @Description: spring 提供的重试机制
 */
public class SpringRetryService {

    /**
     * 利用spring提供的retry机制循环获取
     */
    public void retryGet(){

        // 构建重试模板实例
        RetryTemplate retryTemplate = new RetryTemplate();
        // 设置重试策略，主要设置重试次数和需要捕获的异常
        SimpleRetryPolicy policy = new SimpleRetryPolicy(5, Collections.singletonMap(Exception.class, true));
        // 设置重试回退操作策略，主要设置重试间隔时间
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        //初始间隔
        backOffPolicy.setInitialInterval(1000);
        //最大间隔
        backOffPolicy.setMaxInterval(10 * 1000L);
        //递增倍数（即下次间隔是上次的多少倍）
        backOffPolicy.setMultiplier(2);

        retryTemplate.setRetryPolicy(policy);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        // 通过RetryCallback 重试回调实例包装正常逻辑逻辑，第一次执行和重试执行执行的都是这段逻辑
        final RetryCallback<Object, Exception> retryCallback = context -> {
            System.out.println("开始重试，重试次数为: {}"+context.getRetryCount());
            System.out.println("这里可以调用需要执行的方法进行重试操作");
            return null;
        };
        // 通过RecoveryCallback 重试流程正常结束或者达到重试上限后的退出恢复操作实例
        final RecoveryCallback<Object> recoveryCallback = context -> {
            System.out.println("执行重试结束依然失败后的代码");
            return null;
        };
        try {
            // 由retryTemplate 执行execute方法开始逻辑执行
            retryTemplate.execute(retryCallback, recoveryCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
