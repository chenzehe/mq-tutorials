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
    public static final String QUEUE_NAME = "queue393";
    public static final String EXCHANGE_NAME = "exchange393";

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
        String bindingKey = "*";
        return BindingBuilder.bind(queue).to(exchange).with(bindingKey);
    }

    @Bean
    public SimpleMessageListenerContainer messageContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        //指定消息消费者
        /**
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                String msg = new String(message.getBody());
                log.info("接收到消息: {}", msg);
            }
        });**/

        /**
        MessageListenerAdapter adapter = new MessageListenerAdapter(new QueueListener());
        adapter.setDefaultListenerMethod("listen");
         **/

        MessageListenerAdapter adapter = new MessageListenerAdapter(new QueueListener());
        Map<String, String> queueOrTagToMethodName = new HashMap<>();
        queueOrTagToMethodName.put("queue1", "method1");
        queueOrTagToMethodName.put("queue393", "method393");
        adapter.setQueueOrTagToMethodName(queueOrTagToMethodName);

        container.setMessageListener(adapter);
        return container;
    }
}

