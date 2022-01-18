package com.mq.tutorials.ch4.list101;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import static com.mq.tutorials.ch4.list101.RabbitConfig.*;

import java.io.IOException;

@Slf4j
public class Consumer2 {

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(NAME);
        factory.setPassword(PASSWORD);
        Connection connection;
        final Channel channel;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.basicQos(3);
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            DefaultConsumer defaultConsumer=new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    log.info("接收消息 = {}", message);
                    try {
                        Thread.sleep(4000);
                    }catch (Exception e){ }
                    channel.basicAck(envelope.getDeliveryTag(), true);
                }
            };
            channel.basicConsume(QUEUE_NAME, false, defaultConsumer);
            System.in.read();//等待控制台输入退出程序
        }catch (Exception e){
            log.error("接收消息 异常!", e);
        }
    }
}
