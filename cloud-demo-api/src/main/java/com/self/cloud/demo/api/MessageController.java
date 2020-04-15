package com.self.cloud.demo.api;

import com.self.cloud.demo.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: LiRuiChuan
 * @Date: 2020/3/11 16:16
 * @Description:
 */
@RestController
@RequestMapping("/rabbit")
public class MessageController {

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @GetMapping("/send")
    public String sendMessage(String name){

        try{
            rabbitMQSender.send(name, null);
        }catch (Exception e){
            return "failed";
        }
        return "success";
    }
}
