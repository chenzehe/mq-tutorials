package com.mq.tutorials.ch4.list112;

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
    public static final String WORK_QUEUE_NAME = "work_queue4112";
    public static final String RETRY_QUEUE_NAME = "retry_queue4112";
    public static final String WORK_EXCHANGE_NAME = "work_exchange4112";
    public static final String RETRY_EXCHANGE_NAME = "retry_exchange4112";
    public static final String TTL_QUEUE_NAME = "ttl_queue4112";



    @Bean(WORK_QUEUE_NAME)
    public Queue workQueue(){
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-dead-letter-exchange", RETRY_EXCHANGE_NAME);
        args.put("x-dead-letter-routing-key", "retry-routing-key");
        args.put("x-message-ttl", 10000);
        return new Queue(WORK_QUEUE_NAME, true, false, false, args);
    }

    @Bean(TTL_QUEUE_NAME)
    public Queue ttlQueue() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-expires", 100000);
        return new Queue(TTL_QUEUE_NAME, true, false, false, args);
    }

    @Bean(RETRY_QUEUE_NAME)
    public Queue retryQueue(){
        return new Queue(RETRY_QUEUE_NAME);
    }

    @Bean(WORK_EXCHANGE_NAME)
    public TopicExchange workExchange(){
        return new TopicExchange(WORK_EXCHANGE_NAME);
    }

    @Bean(RETRY_EXCHANGE_NAME)
    public TopicExchange retryExchange(){
        return new TopicExchange(RETRY_EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingWorkQueue(@Qualifier(WORK_QUEUE_NAME) Queue queue, @Qualifier(WORK_EXCHANGE_NAME) TopicExchange exchange){
        String bindingKey = "work-routing-key";
        return BindingBuilder.bind(queue).to(exchange).with(bindingKey);
    }

    @Bean
    public Binding bindingRetryQueue(@Qualifier(RETRY_QUEUE_NAME) Queue queue, @Qualifier(RETRY_EXCHANGE_NAME) TopicExchange exchange){
        String bindingKey = "retry-routing-key";
        return BindingBuilder.bind(queue).to(exchange).with(bindingKey);
    }
}

