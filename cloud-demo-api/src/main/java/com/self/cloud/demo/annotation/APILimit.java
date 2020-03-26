package com.self.cloud.demo.annotation;

import java.lang.annotation.*;

/**
 * @author: liruichuan
 * @Date: 2020/3/26 10:33
 * @Description: 自定义接口限流注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface APILimit {

    /**
     * 资源名称
     * @return
     */
    String name() default "";

    /**
     * 资源对应的key
     * @return
     */
    String key() default "";
    /**
     * key 前缀
     * @return
     */
    String prefix() default "";

    /**
     * 时间限制 单位是秒
     * @return
     */
    int period() default 60;

    /**
     * 限制之间内 访问的并发数量
     * @return
     */
    int count() default 10;

    /**
     * 限制类型
     * @return
     */
    APILimitType type() default  APILimitType.IP;
}
