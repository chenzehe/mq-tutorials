package com.mq.tutorials.ch3.list72;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 类 描 述：TODO
 * 创 建 人：hljuczh@163.com
 * 创建时间：2021/10/9 20:13
 */
@Slf4j
public class ReturnsTest {
    public static final String QUEUE_NAME = "helloQueue72-4";

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
            channel.addReturnListener(new ReturnListener() {
                @Override
                public void handleReturn(int replyCode, String replyText, String exchange, String routingKey,
                                         AMQP.BasicProperties properties, byte[] body) throws IOException {
                    log.info("replyCode={},replyText={},exchange={},routingKey={},properties={},body={}",
                            replyCode,replyText,exchange,routingKey,properties,new String(body));
                }
            });
            String message = "Hello World!";
            channel.basicPublish("", "not-exist",true, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
            System.in.read();//等待控制台输入退出程序
        }catch (Exception e){
            log.error("rabbitmq send message error!");
        }
        finally {
            if(channel !=null) channel.close();
            if(connection != null) connection.close();
        }
    }
}
