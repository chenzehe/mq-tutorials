package com.mq.tutorials.ch3.list53;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类 描 述：TODO
 * 创 建 人：hljuczh@163.com
 * 创建时间：2021/7/8 20:41
 */
@Configuration
public class RabbitConfig {

    public static final String QUEUE_NAME_1 = "helloQueue353_1";
    public static final String QUEUE_NAME_2 = "helloQueue353_2";
    public static final String EXCHANGE_NAME = "helloExchange353";


    @Bean(QUEUE_NAME_1)
    public Queue queue_1(){
        return new Queue(QUEUE_NAME_1);
    }

    @Bean(QUEUE_NAME_2)
    public Queue queue_2(){
        return new Queue(QUEUE_NAME_2);
    }

    @Bean(EXCHANGE_NAME)
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingQueue1(@Qualifier(QUEUE_NAME_1) Queue queue, @Qualifier(EXCHANGE_NAME) TopicExchange exchange){
        String bindingKey = "*.orange.*";
        return BindingBuilder.bind(queue).to(exchange).with(bindingKey);
    }

    @Bean
    public Binding bindingQueue2_1(@Qualifier(QUEUE_NAME_2) Queue queue, @Qualifier(EXCHANGE_NAME) TopicExchange exchange){
        String bindingKey = "*.*.rabbit";
        return BindingBuilder.bind(queue).to(exchange).with(bindingKey);
    }

    @Bean
    public Binding bindingQueue2_2(@Qualifier(QUEUE_NAME_2) Queue queue, @Qualifier(EXCHANGE_NAME) TopicExchange exchange){
        String bindingKey = "lazy.#";
        return BindingBuilder.bind(queue).to(exchange).with(bindingKey);
    }
}

