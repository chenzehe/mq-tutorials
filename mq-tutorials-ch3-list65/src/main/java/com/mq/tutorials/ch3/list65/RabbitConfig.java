package com.mq.tutorials.ch3.list65;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 类 描 述：TODO
 * 创 建 人：hljuczh@163.com
 * 创建时间：2021/7/8 20:41
 */
@Configuration
public class RabbitConfig {
    public static final String QUEUE_NAME = "queue365";
    public static final String EXCHANGE_NAME = "exchange365";

    @Bean(QUEUE_NAME)
    public Queue queue(){
        return new Queue(QUEUE_NAME);
    }

    @Bean(EXCHANGE_NAME)
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingQueue(@Qualifier(QUEUE_NAME) Queue queue, @Qualifier(EXCHANGE_NAME) TopicExchange exchange){
        String bindingKey = "*";
        return BindingBuilder.bind(queue).to(exchange).with(bindingKey);
    }
}

