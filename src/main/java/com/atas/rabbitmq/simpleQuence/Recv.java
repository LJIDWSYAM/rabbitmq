package com.atas.rabbitmq.simpleQuence;

import com.atas.rabbitmq.utils.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv {
    public static final String QUEUE_NAME="test_queue";


    //新的api
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel=connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("new api msg" + msg);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }


    //老的api
    public static void newMethodGetRecv() throws IOException, TimeoutException, InterruptedException {




        Connection connection = ConnectionUtils.getConnection();

        Channel channel=connection.createChannel();

        QueueingConsumer consumer=new QueueingConsumer(channel);

        channel.basicConsume(QUEUE_NAME, true, consumer);

        while (true){

            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String msgString=new String(delivery.getBody());

            System.out.println("recv msg"+msgString);
        }
    }


}
