package com.self.cloud.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liruichuan on 2018/5/29.
 */
@FeignClient(value = "SERVICE-AB",fallback = HelloServiceFallBack.class) //用于通知Feign组件对该接口进行代理(不需要编写接口实现)， SERVICE-AB代理的具体服务
public interface HelloService {
    @RequestMapping("/Info") //对应具体服务中的接口地址（具体服务controller 层的暴露接口）可以指定具体的get/post
    String sayHello();
}
