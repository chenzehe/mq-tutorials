package com.mq.tutorials.ch3.list86;

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

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME, containerFactory = "prefetchCountFactory")
    public void listen(String message) {
        log.info("queueListen接收到消息:{}", message);
        try {
            Thread.sleep(10000);
        }catch (Exception e){ }
        log.info("queueListen处理完消息:{}", message);
    }
}
