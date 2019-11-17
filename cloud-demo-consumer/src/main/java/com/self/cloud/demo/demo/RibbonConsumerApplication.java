package com.self.cloud.demo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by liruichuan on 2018/5/29.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RibbonConsumerApplication {

    @LoadBalanced //开启负载均衡客户端
    @Bean //注册一个具有容错功能的RestTemplate
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(RibbonConsumerApplication.class, args);
    }
}
