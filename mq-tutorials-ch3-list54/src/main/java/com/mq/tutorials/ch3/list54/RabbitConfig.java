package com.mq.tutorials.ch3.list54;

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

    public static final String QUEUE_NAME_1 = "helloQueue354_1";
    public static final String QUEUE_NAME_2 = "helloQueue354_2";
    public static final String EXCHANGE_NAME = "helloExchange354";


    @Bean(QUEUE_NAME_1)
    public Queue queue_1(){
        return new Queue(QUEUE_NAME_1);
    }

    @Bean(QUEUE_NAME_2)
    public Queue queue_2(){
        return new Queue(QUEUE_NAME_2);
    }

    @Bean(EXCHANGE_NAME)
    public HeadersExchange exchange(){
        return new HeadersExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingQueue1(@Qualifier(QUEUE_NAME_1) Queue queue, @Qualifier(EXCHANGE_NAME) HeadersExchange exchange){
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("mq", "rabbitmq");
        headers.put("language", "erlang");
        headers.put("x-match", "any");//这里设置x-match无效，spring-amqp接口whereAll指定x-match为all,whereAny指定x-match值为any
        return BindingBuilder.bind(queue).to(exchange).whereAll(headers).match();
    }

    @Bean
    public Binding bindingQueue2(@Qualifier(QUEUE_NAME_2) Queue queue, @Qualifier(EXCHANGE_NAME) HeadersExchange exchange){
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("mq", "rabbitmq");
        headers.put("language", "java");
        return BindingBuilder.bind(queue).to(exchange).whereAny(headers).match();
    }
}

