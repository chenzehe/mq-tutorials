package com.mq.tutorials.ch3.list94;

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
    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void listen(@Payload byte[] message, @Headers Map<String,Object> headers) {
        log.info("queueListen接收到byte[]消息: {}", new String(message));
        log.info("queueListen接收到headers消息头: {}", headers);

    }
}
