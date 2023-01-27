package io.gao.Topics;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import io.gao.Utils.RabbitmqUtils;

public class Consumer3 {

    public static final String Exchange_name = "topic_test";

    public static void main(String[] args) throws Exception {

        //获取信道
        Channel channel = RabbitmqUtils.getChannel();

        //创建交换机
        channel.exchangeDeclare(Exchange_name, BuiltinExchangeType.TOPIC);
        //创建队列
        channel.queueDeclare("topic02", false, false, false, null);
        //绑定队列和路由key
        channel.queueBind("topic02", Exchange_name, "lazy.#");

        DeliverCallback deliverCallback = (consumerTag, message) -> {
c        };
        CancelCallback cancelCallback = consumerTag -> {
        };
        channel.basicConsume("topic02", true, deliverCallback, cancelCallback);
    }
}
