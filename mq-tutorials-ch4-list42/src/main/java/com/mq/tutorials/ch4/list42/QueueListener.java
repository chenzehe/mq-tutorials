package com.mq.tutorials.ch4.list42;

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
        User user = getUserFromDb(message);
        if(user == null){
            log.info("根据id从数据库中读取user对象为空!");
        }
    }
}
