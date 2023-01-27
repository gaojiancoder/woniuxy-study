package io.gao.Product;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.gao.Utils.RabbitmqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Product {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = RabbitmqUtils.getChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        String msg = "你好";

        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());

        System.out.println("消息发送完毕");
    }

}
