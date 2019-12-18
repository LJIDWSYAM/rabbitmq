package com.atas.rabbitmq.simpleQuence;

import com.atas.rabbitmq.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SendMessage {

    public static final String QUEUE_NAME="test_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        //获得链接
         Connection connection = ConnectionUtils.getConnection();
         //通过链接创建通道
         Channel channel=connection.createChannel();
         //
         channel.queueDeclare(QUEUE_NAME,false,false,false,null);

         String msg="hello simple";

         channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
         System.out.println("send msg"+msg);
         channel.close();
         connection.close();


    }
}
