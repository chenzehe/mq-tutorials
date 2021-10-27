package com.mq.tutorials.ch3.list54;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 类 描 述：TODO
 * 创 建 人：hljuczh@163.com
 * 创建时间：2021/7/8 20:47
 */
@Component
@Slf4j
public class QueueListener {
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME_1)
    public void queue1Listen(Message message) {
        log.info("queue1Listen接收到Message消息: {}", message);
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME_2)
    public void queue2Listen(Message message) {
        log.info("queue2Listen接收到消息: {}", message);
    }
}
