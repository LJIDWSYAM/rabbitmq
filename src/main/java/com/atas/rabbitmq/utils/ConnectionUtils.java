package com.atas.rabbitmq.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtils {
    public static Connection getConnection() throws IOException, TimeoutException {
        //定义链接工厂
        ConnectionFactory factory=new ConnectionFactory();
        //设置服务地址
        factory.setHost("127.0.0.1");
        //设置通信端口
        factory.setPort(5672);

        factory.setVirtualHost("/user_liu");

        factory.setUsername("user_liu");

        factory.setPassword("123");

        return factory.newConnection();
    }
}
