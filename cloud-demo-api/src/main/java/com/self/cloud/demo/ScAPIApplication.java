package com.self.cloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by liruichuan on 2018/5/29.
 */
@SpringBootApplication
//@EnableDiscoveryClient //该注解会根据配置文件中的地址，将服务自身注册到服务注册中心
public class ScAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScAPIApplication.class, args);
    }
}
