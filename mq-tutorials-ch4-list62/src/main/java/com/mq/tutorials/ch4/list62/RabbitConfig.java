package com.mq.tutorials.ch4.list62;

import org.springframework.amqp.core.*;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    public static final String QUEUE_NAME = "462";
    public static final String EXCHANGE_NAME = "exchange462";

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

    @Bean
    public RabbitTemplate rabbitTemplateTransacted(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setChannelTransacted(true);
        return template;
    }
}

