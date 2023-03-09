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
public class DirectReceiver2 {



    @RabbitHandler
    public void process(Map<String,Object> testMessage) throws InterruptedException {
        System.out.println("DirectReceiver2消费者收到消息=========>  : " + testMessage.toString());
        //配合测试预期机制  能者多劳  这个处理较慢  可以接受少一点的消息处理
        Thread.sleep(500);
    }

}
