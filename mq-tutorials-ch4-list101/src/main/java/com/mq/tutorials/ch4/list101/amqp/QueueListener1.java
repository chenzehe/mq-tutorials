package com.mq.tutorials.ch4.list101.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 类 描 述：TODO
 * 创 建 人：hljuczh@163.com
 * 创建时间：2021/7/8 20:47
 */
@Component
@Slf4j
public class QueueListener1 {

    //@RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void listen(String message) {
        log.info("消费者 1 接收到消息:{}", message);
        try {
            Thread.sleep(2000L);
        }catch (Exception e){ }
    }

}
