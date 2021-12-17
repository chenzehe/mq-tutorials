package com.mq.toturials.ch4.list71;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

/**
 * 类 描 述：TODO
 * 创 建 人：hljuczh@163.com
 * 创建时间：2021/7/8 20:47
 */
@Component
@Slf4j
public class QueueListener {

    //@RabbitListener(queues = RabbitConfig.QUEUE_NAME, priority = "5")
    public void listen(Message message) throws Exception{
        log.info("queue lisener 接收到消息:{}", message);
    }

}
