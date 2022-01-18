package com.mq.tutorials.ch4.list93;

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
    public static final String QUEUE_NAME_LAZY = "queueLazy493";
    public static final String QUEUE_NAME_DEFAULT = "queueDefault493";
    public static final String QUEUE_NAME = "queue493";
    public static final String EXCHANGE_NAME = "exchange493";


    @Bean(QUEUE_NAME_LAZY)
    public Queue queueLazy(){
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-queue-mode", "lazy");
        return new Queue(QUEUE_NAME_LAZY,true, false, false, args);
    }

    @Bean(QUEUE_NAME_DEFAULT)
    public Queue queueDefault(){
        return new Queue(QUEUE_NAME_DEFAULT,true, false, false, null);
    }

    @Bean(QUEUE_NAME)
    public Queue queue(){
        return new Queue(QUEUE_NAME);
    }
    @Bean(EXCHANGE_NAME)
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingQueueLazy(@Qualifier(QUEUE_NAME_LAZY) Queue queue, @Qualifier(EXCHANGE_NAME) TopicExchange exchange){
        String bindingKey = "lazy";
        return BindingBuilder.bind(queue).to(exchange).with(bindingKey);
    }

    @Bean
    public Binding bindingQueueDefault(@Qualifier(QUEUE_NAME_DEFAULT) Queue queue, @Qualifier(EXCHANGE_NAME) TopicExchange exchange){
        String bindingKey = "default";
        return BindingBuilder.bind(queue).to(exchange).with(bindingKey);
    }

}

