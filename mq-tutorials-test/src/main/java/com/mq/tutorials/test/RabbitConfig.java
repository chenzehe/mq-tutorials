package com.mq.tutorials.test;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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
    public static final String QUEUE_NAME = "salesCommissionAfterSaleQueue";
    public static final String EXCHANGE_NAME = "customerOrderExchange";


    @Bean(QUEUE_NAME)
    public Queue queue(){
        return new Queue(QUEUE_NAME);
    }

    @Bean(EXCHANGE_NAME)
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingWorkQueu(@Qualifier(QUEUE_NAME) Queue queue, @Qualifier(EXCHANGE_NAME) TopicExchange exchange){
        String bindingKey = "order.qiakr.#.afterSale";
        return BindingBuilder.bind(queue).to(exchange).with(bindingKey);
    }

}

