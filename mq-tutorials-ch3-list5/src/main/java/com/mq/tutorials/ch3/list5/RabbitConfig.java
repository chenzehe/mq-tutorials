package com.mq.tutorials.ch3.list5;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类 描 述：TODO
 * 创 建 人：hljuczh@163.com
 * 创建时间：2021/7/8 20:41
 */
@Configuration
public class RabbitConfig {

    public static final String QUEUE_NAME = "helloQueue35";
    public static final String EXCHANGE_NAME = "helloExchange35";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        String bindingKey = "com.mq.#";
        return BindingBuilder.bind(queue).to(exchange).with(bindingKey);
    }


}

