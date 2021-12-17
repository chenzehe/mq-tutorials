package com.mq.tutorials.ch4.list1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * 类 描 述：TODO
 * 创 建 人：hljuczh@163.com
 * 创建时间：2021/10/9 20:16
 */
@Slf4j
public class ConfirmsSyncMultipleTest {
    public static final String QUEUE_NAME = "helloQueue41-2";

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
            String message = "Hello World!";
            for(int i = 0; i < 10; i++) {
                channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
            }
            if(channel.waitForConfirms()){//发送多条消息后调用waitForConfirms()方法阻塞等待
                log.info("sendMessage waitForConfirms is true : {}", message);
            }else{
                log.info("sendMessage waitForConfirms is false : {}", message);
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
