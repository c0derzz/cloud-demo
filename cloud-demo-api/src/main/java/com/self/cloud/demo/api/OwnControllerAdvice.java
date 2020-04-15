package com.self.cloud.demo.api;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: liruichuan
 * @Date: 2020/3/26 11:34
 * @Description: 统一异常处理
 */
@RestControllerAdvice
@ControllerAdvice
public class OwnControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handle(Exception e){
        return e.getMessage();

    }
}
