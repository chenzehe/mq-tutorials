package com.mq.tutorials.ch4.list23;

import lombok.extern.slf4j.Slf4j;
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

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void listen(String message) {
        log.info("queueListen接收到消息:{}", message);
        int i = 1 / 0;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME_2, concurrency = "2-3")
    public void listen2(String message) throws Exception{
        log.info("queue2Listen接收到消息:{}", message);
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME_2, concurrency = "1")
    public void listen22(String message) throws Exception{
        log.info("queue22Listen接收到消息:{}", message);
    }
}
