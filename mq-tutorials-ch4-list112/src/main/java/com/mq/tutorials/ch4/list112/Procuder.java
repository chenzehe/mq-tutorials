package com.mq.tutorials.ch4.list112;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 类 描 述：TODO
 * 创 建 人：hljuczh@163.com
 * 创建时间：2021/7/8 20:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class Procuder {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void sendMessage() {
        String message = "Hello World!";
        this.amqpTemplate.convertAndSend(RabbitConfig.WORK_EXCHANGE_NAME, "work-routing-key", message);
        log.info("sendMessage finished : {}", message);
    }

    @Test
    public void sendTTLMessage() {
        String messageStr = "Hello World!";
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setExpiration("10000");
        Message message = new Message(messageStr.getBytes(), messageProperties);
        log.info("sendMessage start : {}", message);
        this.amqpTemplate.convertAndSend(RabbitConfig.WORK_EXCHANGE_NAME, "work-routing-key", message);
        log.info("sendMessage finished : {}", message);
    }

}