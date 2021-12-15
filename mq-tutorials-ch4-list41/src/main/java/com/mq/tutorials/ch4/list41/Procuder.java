package com.mq.tutorials.ch4.list41;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import static com.mq.tutorials.ch4.list41.RabbitConfig.*;

@Slf4j
public class Procuder {

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
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC,true);
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME,"key");
            String message = "Hello World!";
            channel.txSelect();
            channel.basicPublish(EXCHANGE_NAME, "key", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
            channel.txCommit();
            log.info("rabbitmq send message = {}", message);
        } catch (Exception e) {
            log.error("rabbitmq send message error!", e);
            channel.txRollback();
        } finally {
            if (channel != null) channel.close();
            if (connection != null) connection.close();
        }
    }
}