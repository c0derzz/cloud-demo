package com.self.cloud.demo.service;

import com.rabbitmq.client.Channel;
import com.self.cloud.demo.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Auther: LiRuiChuan
 * @Date: 2020/3/13 14:31
 * @Description: 消费者
 */
@Component
public class RabbitMQReciver {


    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(RabbitMQConfig.EXCHANGE_NAME),
            value=@Queue(RabbitMQConfig.QUEUE_NAME),
            key=RabbitMQConfig.ROUTING_KEY))
    @RabbitHandler
    public void onMessage(org.springframework.messaging.Message message, Channel channel) throws Exception{
        System.err.println("消费端Payload: " + message.getPayload());
        Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK,获取deliveryTag
        /********** 消费端的消息确认 ************/
        // 消息确认,false:只是对当前信息的确认
        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            channel.basicNack(deliveryTag, false, true);
        }

    }
}
