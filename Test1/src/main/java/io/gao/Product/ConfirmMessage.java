package io.gao.Product;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import io.gao.Utils.RabbitmqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConfirmMessage {
    public static final String queue_name = "confirm";

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {

//        ConfirmMessage.MessageOne(); //201
        ConfirmMessage.MessageTwo(); //59
//        ConfirmMessage.MessageOther(); //51
    }

    public static void MessageOne() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitmqUtils.getChannel();
        //创建队列
        channel.queueDeclare(queue_name, true, false, false, null);
        //开启发布确认
        channel.confirmSelect();
        //开始时间
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            String msg = i + "";
            channel.basicPublish("", queue_name, null, msg.getBytes());
            boolean b = channel.waitForConfirms();
            if (b) {
                System.out.println("发送成功");
            }
        }
        //结束时间
        long end = System.currentTimeMillis();
        System.out.println("时间" + (end - start));
    }

    public static void MessageTwo() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitmqUtils.getChannel();
        //创建队列
        //开启发布确认
        channel.confirmSelect();
        //开始时间
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            String msg = i + "";
            channel.basicPublish("", queue_name, null, msg.getBytes());

            if (i % 100 == 0) {
                boolean b = channel.waitForConfirms();
            }


        }
        //结束时间
        long end = System.currentTimeMillis();
        System.out.println("时间" + (end - start));
    }


    //异步发布确认
    public static void MessageOther() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitmqUtils.getChannel();
        //创建队列
        //开启发布确认
        channel.confirmSelect();
        //消息确认接收成功回调函数
        /**
         * deliveryTag ：消息的标识
         * multiple： 是否批量确认
         */
        ConfirmCallback ackCallback = (deliveryTag, multiple) -> {
            System.out.println("确认的消息"+deliveryTag);
        };
        //消息确认接收失败回调函数
        /**
         * deliveryTag ：消息的标识
         * multiple： 是否批量确认
         */
        ConfirmCallback nackCallback = (deliveryTag, multiple) -> {
            System.out.println("未确认的消息"+deliveryTag);
        };
         //异步发布监听器
        channel.addConfirmListener(ackCallback, nackCallback);

        //开始时间
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            String msg = i + "";
            channel.basicPublish("", queue_name, null, msg.getBytes());
        }


        //结束时间
        long end = System.currentTimeMillis();
        System.out.println("时间" + (end - start));
    }


}
