package io.gao.Config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.Binding;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

@Configuration
public class DelayedQueueConfig {
    //队列
    public static final String DELAYED_QUEUE_NAME = "delayed.queue";
    //交换机
    public static final String DELAYED_EXCHANGE_NAME = "delayed.exchange";
    //routingkey
    public static final String DELAYED_ROUTING_KEY = "delayed.routingkey";
    //声明交换机
    @Bean
    public CustomExchange delayedExchange() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type", "direct");
        return new CustomExchange(DELAYED_EXCHANGE_NAME, "x_delayed-massage", true, false, arguments);
    }
//创建队列
    public Queue delayedQueue(){
        return new Queue(DELAYED_QUEUE_NAME);
    };
    //绑定
    @Bean
    public Binding delayedQueueBindingDelayedExchange(
            @Qualifier(delayedQueue) Queue delayedQueue,
            @Qualifier(delayedExchange) CustomExchange delayedExchange
    ){
      return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(DELAYED_ROUTING_KEY).noargs();
    };
}
