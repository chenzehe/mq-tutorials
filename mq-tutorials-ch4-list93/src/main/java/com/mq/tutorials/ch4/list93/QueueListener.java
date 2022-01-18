package com.mq.tutorials.ch4.list93;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类 描 述：TODO
 * 创 建 人：hljuczh@163.com
 * 创建时间：2021/7/8 20:47
 */
@Component
@Slf4j
public class QueueListener {

    private AtomicInteger num = new AtomicInteger();

    //@RabbitListener(queues = RabbitConfig.QUEUE_NAME_DEFAULT)
    public void queueListenDefault(String message){
        int currentNum = num.incrementAndGet();
        if(currentNum == 1 || currentNum == 1000000) {
            log.info("default queue lisener 接收到消息:{}", message);
        }
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME_LAZY)
    public void queueListenLazy(String message){
        int currentNum = num.incrementAndGet();
        if(currentNum == 1 || currentNum == 1000000) {
            log.info("lazy queue lisener 接收到消息:{}", message);
        }    }


}
