package com.mq.tutorials.ch4.list42;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;

@Component
public class AmqpTemplateUtils {
    @Resource
    private AmqpTemplate amqpTemplate;
    public void sendMessage(String exchange,String routingKey, String message) {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() {
                    amqpTemplate.convertAndSend(exchange, routingKey, message);
                }
            });
        } else {
            amqpTemplate.convertAndSend(exchange, routingKey, message);
        }
    }
}