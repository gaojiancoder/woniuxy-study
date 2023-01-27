package io.gao.Consumer;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import io.gao.Utils.RabbitmqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Work02 {
    public static final String queue_name = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitmqUtils.getChannel();

        DeliverCallback deliverCallback = (consumerTag, message) -> {

            System.out.println("消息被接收:" + new String(message.getBody()));

            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        };

        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("消息接收失败" + consumerTag);
        };
        channel.basicConsume(queue_name,false,deliverCallback,cancelCallback);
    }
}
