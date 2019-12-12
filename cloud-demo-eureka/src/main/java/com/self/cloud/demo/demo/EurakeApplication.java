package com.self.cloud.demo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by liruichuan on 2018/5/29.
 */
 //启动一个服务注册中心提供给其他应用进行对话
@EnableEurekaServer
@SpringBootApplication //注解等价于以默认属性使用 @Configuration ，@EnableAutoConfiguration 和 @ComponentScan
public class EurakeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurakeApplication.class,args);
    }
}
