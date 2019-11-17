package com.self.cloud.demo.demo.controller;

import com.self.cloud.demo.config.PropertiesBean;
import com.self.cloud.demo.demo.config.PropertiesBean;
import com.self.cloud.demo.demo.service.HelloService;
import com.self.cloud.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by liruichuan on 2018/5/29.
 */
@RestController
public class ConsumerController {

    @Autowired
    HelloService helloService;

    @Resource
    private PropertiesBean propertiesBean;

    @Value("${OS}")
    private String OS;

    @Value("${from}")
    private String from;

    @Value("${password}")
    private String password;

    @RequestMapping(value = "/feign-customer")
    public String helloCustomer() {
        return helloService.sayHello();
    }

    @RequestMapping("/envInfo")
    public String envInfo(){
        return "操作系统为："+OS+"--"+from+"--"+propertiesBean.getPassword()+"解密密码："+password;
    }
}
