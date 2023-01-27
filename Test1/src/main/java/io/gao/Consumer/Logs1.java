package io.gao.Consumer;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import io.gao.Utils.RabbitmqUtils;

public class Logs1 {
    public static final String exchange_name="test_fanout";
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitmqUtils.getChannel();//获取信道
        //创建交换机
        channel.exchangeDeclare(exchange_name,"fanout");
        //生成临时队列
        String queue = channel.queueDeclare().getQueue();
        //队列绑定
        channel.queueBind(queue,exchange_name,"");
        //如何处理接收的消息
        DeliverCallback deliverCallback=  (consumerTag,  message) ->{
            System.out.println("打印的消息"+new String(message.getBody()));
        };
        //未成功接收的消息
        CancelCallback cancelCallback=consumerTag->{};
        /**
         * 队列名，是否自动确认，回调函数
         */
        channel.basicConsume(queue,true,deliverCallback,cancelCallback);
    }
}
