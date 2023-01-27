package io.gao.Consumer;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import io.gao.Utils.RabbitmqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Work01 {
    public static final String queue_name = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitmqUtils.getChannel();

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("消息被接收:" + new String(message.getBody()));
        };

        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("消息接收失败" + consumerTag);
        };
//工作模式 不公平模式
        channel.basicQos(1);
        System.out.println("c2等待中。。。。");
        channel.basicConsume(queue_name, true, deliverCallback, cancelCallback);


    }
}
