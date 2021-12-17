package com.mq.tutorials.ch4.list1;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 类 描 述：TODO
 * 创 建 人：hljuczh@163.com
 * 创建时间：2021/10/9 20:13
 */
@Slf4j
public class ConfirmsAsyncTest {
    public static final String QUEUE_NAME = "helloQueue41-3";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("xmall");
        factory.setPassword("yiguokeji01rabbit");
        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.confirmSelect();//开启confirm模式
            channel.addConfirmListener(new ConfirmListener(){
                @Override
                public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                    log.info("消息确认成功，回调handleAck方法,deliveryTag={},multiple={}",deliveryTag,multiple);
                }
                @Override
                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                    log.info("消息确认不成功，回调handleNack方法,deliveryTag={},multiple={}",deliveryTag,multiple);
                }
            });
            String message = "Hello World!";
            for(int i = 0; i < 10; i++) {
                channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
            }
        }catch (Exception e){
            log.error("rabbitmq send message error!");
        }
        finally {
            if(channel !=null) channel.close();
            if(connection != null) connection.close();
        }
    }
}
