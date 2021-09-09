package com.mq.tutorials.ch3.list65;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类 描 述：TODO
 * 创 建 人：hljuczh@163.com
 * 创建时间：2021/7/8 20:51
 */
@Component
@Slf4j
public class Procuder {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage() {
        String messageStr = "Hello World!";
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("hello", "hello world!");
        Message message = new Message(messageStr.getBytes(), messageProperties);
        this.amqpTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, "hello", message);
        log.info("sendMessage finished : " + message);
    }

}
