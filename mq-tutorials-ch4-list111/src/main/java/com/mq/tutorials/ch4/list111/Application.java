package com.mq.tutorials.ch4.list111;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 类 描 述：TODO
 * 创 建 人：hljuczh@163.com
 * 创建时间：2021/7/12 19:11
 */
@SpringBootApplication
public class Application {
    public static void main(String[] arguments) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.run(arguments);
    }
}
