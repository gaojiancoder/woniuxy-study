package io.gao.Topics;

import com.rabbitmq.client.Channel;
import io.gao.Utils.RabbitmqUtils;

import java.util.Scanner;

public class Product {

    public static final String Exchange_name = "topic_test";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitmqUtils.getChannel();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            String msg=scanner.next();
            channel.basicPublish(Exchange_name,"a.orange.rabbit",null,msg.getBytes());
        }

    }

}
