package com.mq.tutorials.ch4.list111;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * 类 描 述：TODO
 * 创 建 人：hljuczh@163.com
 * 创建时间：2021/7/8 20:47
 */
@Component
@Slf4j
public class QueueListener {

    @RabbitListener(queues = RabbitConfig.WORK_QUEUE_NAME)
    public void listen(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        log.info("work queue listener 接收到消息，:{}", message);
        //int i = 1 / 0;
        try {
            channel.basicNack(tag, false,false);
        }catch (Exception e){

        }
    }

    @RabbitListener(queues = RabbitConfig.DELAYED_QUEUE_NAME)
    public void listen2(String message) throws Exception{
        log.info("delayed queue lisener 接收到消息:{}", message);
    }

}
