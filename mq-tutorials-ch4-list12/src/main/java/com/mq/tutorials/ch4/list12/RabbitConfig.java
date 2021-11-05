package com.mq.tutorials.ch4.list12;

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
    public static final String DELAYED_QUEUE_NAME = "delayed_queue412";
    public static final String DELAYED_EXCHANGE_NAME = "delayed_exchange412";

    @Bean
    public CustomExchange delayedExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAYED_EXCHANGE_NAME, "x-delayed-message", true, false, args);
    }

    @Bean(DELAYED_QUEUE_NAME)
    public Queue delayedQueue(){
        return new Queue(DELAYED_QUEUE_NAME);
    }

    @Bean
    public Binding bindingDelayedQueue(@Qualifier(DELAYED_QUEUE_NAME) Queue queue, @Qualifier(DELAYED_EXCHANGE_NAME) CustomExchange exchange){
        String bindingKey = "delayed-routing-key";
        return BindingBuilder.bind(queue).to(exchange).with(bindingKey).noargs();
    }

}

