package com.mq.tutorials.ch3.list103;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 类 描 述：TODO
 * 创 建 人：hljuczh@163.com
 * 创建时间：2021/7/8 20:47
 */
@Component
@Slf4j
public class QueueListener {
    //@RabbitListener(queues = RabbitConfig.QUEUE_NAME, containerFactory = "containerFactory")
    public void listen(User user) {
        log.info("queueListen接收到消息: {}", user.toString());
    }
}
