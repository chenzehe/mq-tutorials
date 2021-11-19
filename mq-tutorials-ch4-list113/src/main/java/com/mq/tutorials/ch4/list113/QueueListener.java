package com.mq.tutorials.ch4.list113;

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

    @RabbitListener(queues = RabbitConfig.DELAYED_QUEUE_NAME)
    public void listen(String message) throws Exception{
        log.info("delayed queue lisener 接收到消息:{}", message);
    }

}
