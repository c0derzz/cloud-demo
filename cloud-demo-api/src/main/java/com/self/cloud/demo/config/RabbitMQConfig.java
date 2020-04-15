package com.self.cloud.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: LiRuiChuan
 * @Date: 2020/3/11 16:31
 * @Description: rabbitMq配置
 */
@Configuration
public class RabbitMQConfig {

    public final static String ROUTING_KEY = "selfflyer";
    public final static String QUEUE_NAME = "selfflyer.queue";
    public final static String EXCHANGE_NAME = "selfflyer.exchange";
    /**
     * 队列
     * @return
     */
    @Bean
    public Queue selfflyerQueue(){
        return new Queue(QUEUE_NAME,true);
    }

    /**
     * 转发器 可以是DirectExchange\FanoutExchange\TopicExchange
     * @return
     */
    @Bean
    public DirectExchange selfflyerExchange(){
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(selfflyerQueue()).to(selfflyerExchange()).with(ROUTING_KEY);
    }
}
