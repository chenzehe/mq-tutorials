package com.mq.tutorials.ch3.list103;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
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
        User user = new User();
        user.setId(1L);
        user.setName("hello world");
        //user.setAge(10);
        this.amqpTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, "user", user);
        log.info("消息发送完成: {}", user.toString());
    }

}
