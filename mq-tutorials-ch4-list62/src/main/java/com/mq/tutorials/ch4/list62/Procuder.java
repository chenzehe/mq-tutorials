package com.mq.tutorials.ch4.list62;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    private AmqpTemplateUtils amqpTemplateUtils;

    @Test
    public void sendMessage() {
        User user = insertUser2Db(User user);
        amqpTemplateUtils.sendMessage(RabbitConfig.EXCHANGE_NAME, "key", user.getUserId());
    }
}
