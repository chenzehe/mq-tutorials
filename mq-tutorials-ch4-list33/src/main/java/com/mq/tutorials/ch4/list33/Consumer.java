package com.mq.tutorials.ch4.list33;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.mq.tutorials.ch4.list33.RabbitConfig.*;

@Slf4j
public class Consumer {

    public static final String QUEUE_NAME = "queue441";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(NAME);
        factory.setPassword(PASSWORD);
        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.basicQos(1);
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    log.info("rabbitmq received message, consumerTag = {}, properties = {}, message = {}", consumerTag, properties, message);
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {

                    }
                }
            };
            channel.basicConsume(QUEUE_NAME, false, defaultConsumer);
            System.in.read();//等待控制台输入退出程序
        } catch (Exception e) {
            log.error("rabbitmq received message error!", e);
        } finally {
            if (channel != null) channel.close();
            if (connection != null) connection.close();
        }
    }
}
