package com.mq.tutorials.ch3.list93;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 类 描 述：TODO
 * 创 建 人：hljuczh@163.com
 * 创建时间：2021/7/8 20:47
 */
@Component
@Slf4j
public class QueueListener {
    public void listen(String message) {
        log.info("queueListen接收到消息: {}", message);
    }

    public void method393(String message) {
        log.info("queueListen method393 接收到消息: {}", message);
    }
}
