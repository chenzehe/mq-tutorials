package com.mq.tutorials.ch2.list22;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类 描 述：TODO
 * 创 建 人：hljuczh@163.com
 * 创建时间：2021/7/8 20:41
 */
@Configuration
public class RabbitConfig {

    @Value("${spring.rabbitmq.host}")
    public String HOST;
    @Value("${spring.rabbitmq.port}")
    public Integer PORT;
    @Value("${spring.rabbitmq.username}")
    public String USERNAME;
    @Value("${spring.rabbitmq.password}")
    public String PASSWORD;

    public static final String QUEUE_NAME = "customerQueue222";
    public static final String EXCHANGE_NAME = "customerExchange222";

    /**
     * virtual host customer
     */
    @Bean(name = "connectionFactoryCustomer")
    public ConnectionFactory connectionFactoryCustomer() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(HOST);
        connectionFactory.setPort(PORT);
        connectionFactory.setUsername(USERNAME);
        connectionFactory.setPassword(PASSWORD);
        connectionFactory.setVirtualHost("customer");
        return connectionFactory;
    }

    @Bean(name = "rabbitAdminCustomer")
    public RabbitAdmin rabbitAdminCustomer(@Qualifier("connectionFactoryCustomer") ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    public Queue queue(@Qualifier("rabbitAdminCustomer") RabbitAdmin rabbitAdminCustomer){
        Queue queue = QueueBuilder.durable(QUEUE_NAME).build();
        rabbitAdminCustomer.declareQueue(queue);
        return queue;
    }

    public TopicExchange exchange(@Qualifier("rabbitAdminCustomer") RabbitAdmin rabbitAdminCustomer){
        TopicExchange topicExchange = new TopicExchange(EXCHANGE_NAME);
        rabbitAdminCustomer.declareExchange(topicExchange);
        return topicExchange;
    }

    @Bean
    public Binding declareQueue(@Qualifier("rabbitAdminCustomer") RabbitAdmin rabbitAdminCustomer) {
        Binding binding = BindingBuilder.bind(this.queue(rabbitAdminCustomer))
                .to(this.exchange(rabbitAdminCustomer))
                .with("com.mq.#");
        rabbitAdminCustomer.declareBinding(binding);
        return binding;
    }

    @Bean(name = "rabbitTemplateCustomer")
    public RabbitTemplate rabbitTemplateCustomer(@Qualifier("connectionFactoryCustomer") ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean(name = "rabbitListenerContainerFactoryCustomer")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactoryCustomer(@Qualifier("connectionFactoryCustomer") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConcurrentConsumers(2);
        simpleRabbitListenerContainerFactory.setPrefetchCount(2);
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        return simpleRabbitListenerContainerFactory;
    }


}

