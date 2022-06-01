package com.mq.tutorials.ch3.list93;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 类 描 述：TODO
 * 创 建 人：hljuczh@163.com
 * 创建时间：2021/7/8 20:41
 */
@Configuration
@Slf4j
public class RabbitConfig {
    public static final String QUEUE_NAME_393 = "queue393";
    public static final String QUEUE_NAME_394 = "queue394";
    public static final String[] QUEUES_NAME = {QUEUE_NAME_393,QUEUE_NAME_394};
    public static final String EXCHANGE_NAME = "exchange393";

    @Bean(QUEUE_NAME_393)
    public Queue queue393(){
        return new Queue(QUEUE_NAME_393);
    }

    @Bean(QUEUE_NAME_394)
    public Queue queue394(){
        return new Queue(QUEUE_NAME_394);
    }

    @Bean(EXCHANGE_NAME)
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingQueue393(@Qualifier(QUEUE_NAME_393) Queue queue, @Qualifier(EXCHANGE_NAME) TopicExchange exchange){
        String bindingKey = "*";
        return BindingBuilder.bind(queue).to(exchange).with(bindingKey);
    }

    @Bean
    public Binding bindingQueue394(@Qualifier(QUEUE_NAME_394) Queue queue, @Qualifier(EXCHANGE_NAME) TopicExchange exchange){
        String bindingKey = "*";
        return BindingBuilder.bind(queue).to(exchange).with(bindingKey);
    }

    @Bean
    public SimpleMessageListenerContainer messageContainer393(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(QUEUE_NAME_393);
        //指定消息消费者
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                log.info("接收到消息: {}", message);
                Thread.sleep(5000L);
                log.info("结束消息: {}", message);
            }
        });

        /**
        MessageListenerAdapter adapter = new MessageListenerAdapter(new QueueListener());
        adapter.setDefaultListenerMethod("listen");
        **/

        /**
        MessageListenerAdapter adapter = new MessageListenerAdapter(new QueueListener());
        Map<String, String> queueOrTagToMethodName = new HashMap<>();
        queueOrTagToMethodName.put("queue394", "method1");
        queueOrTagToMethodName.put("queue393", "method1");
        adapter.setQueueOrTagToMethodName(queueOrTagToMethodName);
        container.setMessageListener(adapter);
         **/


        return container;
    }

    @Bean
    public SimpleMessageListenerContainer messageContainer394(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(QUEUE_NAME_394);
        //指定消息消费者
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                log.info("接收到消息: {}", message);
                Thread.sleep(5000L);
                log.info("结束消息: {}", message);
            }
        });

        /**
         MessageListenerAdapter adapter = new MessageListenerAdapter(new QueueListener());
         adapter.setDefaultListenerMethod("listen");
         **/

        /**
         MessageListenerAdapter adapter = new MessageListenerAdapter(new QueueListener());
         Map<String, String> queueOrTagToMethodName = new HashMap<>();
         queueOrTagToMethodName.put("queue394", "method1");
         queueOrTagToMethodName.put("queue393", "method1");
         adapter.setQueueOrTagToMethodName(queueOrTagToMethodName);
         container.setMessageListener(adapter);
         **/


        return container;
    }

}

