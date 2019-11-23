package com.self.cloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by liruichuan on 2018/5/29.
 */

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients //开启spring cloud feign的支持
public class FeignConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignConsumerApplication.class, args);

        /*ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(FeignConsumerApplication.class, args);
        String property = configurableApplicationContext.getEnvironment().getProperty("env");
        System.out.println("command param:"+property);*/
    }
}
