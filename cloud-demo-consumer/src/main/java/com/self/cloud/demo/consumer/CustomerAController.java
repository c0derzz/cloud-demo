package com.self.cloud.demo.consumer;

import com.self.cloud.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liruichuan on 2018/5/29.
 */
@RestController
public class CustomerAController {

    @Value("${OS}")
    private String OS;

    @Value("${env}")
    private String env;

    @Autowired
    private HelloService service;

    @RequestMapping("/ribbon-consumer")
    public String coutomerA() {
        return service.helloService();
    }

    @RequestMapping("/envInfo")
    public String envInfo(){

        return "操作系统为："+OS+"--"+env;
    }
}
