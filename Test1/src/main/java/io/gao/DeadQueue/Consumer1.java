package io.gao.DeadQueue;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import io.gao.Utils.RabbitmqUtils;

import java.util.HashMap;
import java.util.Map;

public class Consumer1 {
    //普通交换机和普通队列
    public static final String NORMAL_EXCHANGE_NAME = "normal_exchange_name";
    public static final String NORMAL_QUEUE_NAME = "normal_queue_name";
    //死信交换机 和死信队列
    public static final String DEAD_EXCHANGE_NAME = "dead_exchange_name";
    public static final String DEAD_QUEUE_NAME = "dead_queue_name";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitmqUtils.getChannel();
        //创建交换机
        channel.exchangeDeclare(NORMAL_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(DEAD_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        //声明普通队列
        //普通队列将信息传递到死信队列上需要的参数
        Map<String, Object> arguments = new HashMap<>();
        //正常队列设置死信传递到那个死信交换机
        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE_NAME);
        //绑定死信路由key
        arguments.put("x-dead-letter-routing-key", "lisi");
        channel.queueDeclare(NORMAL_QUEUE_NAME, true, false, false, arguments);
        //声明死信队列
        channel.queueDeclare(DEAD_QUEUE_NAME, true, false, false, null);
        //绑定队列
        channel.queueBind(NORMAL_QUEUE_NAME, NORMAL_EXCHANGE_NAME, "zhangsan");
        channel.queueBind(DEAD_QUEUE_NAME, DEAD_EXCHANGE_NAME, "lisi");
        //接收成功的回调函数
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("Consumer1接收到的消息：" + new String(message.getBody(), "UTF-8"));
        };
        //接收消息
        channel.basicConsume(NORMAL_QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });
    }
}
