package io.gao.Consumer;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import io.gao.Utils.RabbitmqUtils;

public class Logs2 {
    public static final String exchange_name="test_fanout";
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitmqUtils.getChannel();

        channel.exchangeDeclare(exchange_name,"fanout");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,exchange_name,"");

        DeliverCallback deliverCallback=  (consumerTag,  message) ->{
            System.out.println("2打印的消息"+new String(message.getBody()));
        };
        CancelCallback cancelCallback=consumerTag->{

        };

        channel.basicConsume(queue,true,deliverCallback,cancelCallback);
    }
}
