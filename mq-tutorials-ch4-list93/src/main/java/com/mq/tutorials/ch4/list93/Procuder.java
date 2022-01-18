package com.mq.tutorials.ch4.list93;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 类 描 述：TODO
 * 创 建 人：hljuczh@163.com
 * 创建时间：2021/7/8 20:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class Procuder {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Before
    public void createNewConnection(){
        //下面测试发送消息时，执行此方法先去创建Connection对象比较耗时，提前把Connection对象创建出来
        amqpTemplate.receive(RabbitConfig.QUEUE_NAME);
    }

    @Test
    public void sendMessageDefault() {
        String message = createMessage(1000);
        Integer messageNum = 1000000;
        log.info("开始发送消息...");
        Long startTime = System.currentTimeMillis();
        for(int i = 0; i < messageNum; i++) {
            this.amqpTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, "default", message);
        }
        long endTime = System.currentTimeMillis();
        log.info("消息发送完成，消耗时间:{}毫秒", (endTime-startTime));
    }

    @Test
    public void sendMessageLazy() {
        String message = createMessage(1000);
        Integer messageNum = 1000000;
        log.info("开始发送消息...");
        Long startTime = System.currentTimeMillis();
        for(int i = 0; i < messageNum; i++) {
            this.amqpTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, "lazy", message);
        }
        long endTime = System.currentTimeMillis();
        log.info("消息发送完成，消耗时间:{}毫秒", (endTime-startTime));
    }

    private String createMessage(Integer length){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length ; i ++){
            sb.append("a");
        }
        return sb.toString();
    }

}
