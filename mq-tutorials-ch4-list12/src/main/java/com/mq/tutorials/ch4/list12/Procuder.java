package com.mq.tutorials.ch4.list12;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        String messageStr = "Hello World!";
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("x-delay", 5000);
        Message message = new Message(messageStr.getBytes(), messageProperties);
        this.amqpTemplate.convertAndSend(RabbitConfig.DELAYED_EXCHANGE_NAME, "delayed-routing-key", message);
        log.info("sendMessage finished : {}", message);
    }

}
