package com.mq.tutorials.ch3.list86;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
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
    public static final String QUEUE_NAME = "queue3862";
    public static final String EXCHANGE_NAME = "exchange3862";

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
        String bindingKey = "key";
        return BindingBuilder.bind(queue).to(exchange).with(bindingKey);
    }

    @Bean(name = "prefetchCountFactory")
    public SimpleRabbitListenerContainerFactory prefetchCountFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory prefetchCountFactory =
                new SimpleRabbitListenerContainerFactory();
        prefetchCountFactory.setConcurrentConsumers(1);
        prefetchCountFactory.setPrefetchCount(10);
        prefetchCountFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        prefetchCountFactory.setConnectionFactory(connectionFactory);
        return prefetchCountFactory;
    }
}

