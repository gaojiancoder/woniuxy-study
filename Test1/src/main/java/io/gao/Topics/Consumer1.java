package io.gao.Topics;

import com.rabbitmq.client.*;
import io.gao.Utils.RabbitmqUtils;

public class Consumer1 {

    public static final String Exchange_name = "topic_test";

    public static void main(String[] args) throws Exception {

        //获取信道
        Channel channel = RabbitmqUtils.getChannel();

        //创建交换机
        channel.exchangeDeclare(Exchange_name, BuiltinExchangeType.TOPIC);
        //创建队列
        channel.queueDeclare("topic01", false, false, false, null);
        //绑定队列和路由key
        channel.queueBind("topic01", Exchange_name, "*.orange.*");

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("orange" + new String(message.getBody()));
        };
        CancelCallback cancelCallback = consumerTag -> {
        };
        channel.basicConsume("topic01", true, deliverCallback, cancelCallback);
    }
}
