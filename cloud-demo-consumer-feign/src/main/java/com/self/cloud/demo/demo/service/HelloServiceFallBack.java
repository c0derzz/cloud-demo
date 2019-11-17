package com.self.cloud.demo.demo.service;

/**
 * Created by liruichuan on 2018/5/29.
 */
public class HelloServiceFallBack implements HelloService{
    @Override
    public String sayHello() {
        return "Error occur";
    }
}
