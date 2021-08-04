package com.mq.tutorials.ch2.list1;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {

    public static final String QUEUE_NAME = "helloQueue21";

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
            DefaultConsumer defaultConsumer=new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("rabbitmq received message, consumerTag = " + consumerTag + ", " +
                            "properties = " + properties.toString() +", message = " + message);
                }
            };
            channel.basicConsume(QUEUE_NAME, true, defaultConsumer);
            System.in.read();//等待控制台输入退出程序
        }catch (Exception e){
            System.out.println("rabbitmq received message error!");
            e.printStackTrace();
        }
        finally {
            if(channel !=null) channel.close();
            if(connection != null) connection.close();
        }
    }
}
