package com.mq.tutorials.ch3.list5;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 类 描 述：TODO
 * 创 建 人：hljuczh@163.com
 * 创建时间：2021/7/8 20:47
 */
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_NAME)
@Slf4j
public class QueueListener {
    @RabbitHandler
    public void listen(String message) {
        log.info("QueueListener接收到消息: {}", message);
    }
}
