package io.gao.DeadQueue;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.impl.AMQBasicProperties;
import io.gao.Utils.RabbitmqUtils;

public class Product {
    public static final String NORMAL_EXCHANGE_NAME = "normal_exchange_name";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitmqUtils.getChannel();
        //设置死信TTL时间
        AMQP.BasicProperties properties = new AMQP.BasicProperties()
                .builder()
                .expiration("100000")
                .build();
//for循环发送十条消息
        for (int i = 1; i < 11; i++) {
            String msg = "" + i;
            channel.basicPublish(NORMAL_EXCHANGE_NAME, "zhangsan", properties, msg.getBytes());
        }
    }
}
