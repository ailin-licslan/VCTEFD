package com.lin.licslan.mq.consumer.direct;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 * @author licslan
 * */
@Component
@RabbitListener(queues = "licslan-test")//监听的队列名称 licslan-test
public class DirectReceiver1 {



    @RabbitHandler
    public void process(Map<String,Object> testMessage) {
        System.out.println("DirectReceiver1消费者收到消息  : " + testMessage.toString());
    }

}
