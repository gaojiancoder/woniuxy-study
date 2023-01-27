package io.gao.Product;

import com.rabbitmq.client.Channel;
import io.gao.Utils.RabbitmqUtils;

import java.util.Scanner;

public class EmitLog {
    public static final String exchange_name="test_fanout";
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitmqUtils.getChannel();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            String msg =scanner.next();
            channel.basicPublish(exchange_name,"",null,msg.getBytes());
            System.out.println("输出的数据："+msg);
        }


    }
}
