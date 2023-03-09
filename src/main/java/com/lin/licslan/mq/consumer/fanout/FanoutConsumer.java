package com.lin.licslan.mq.consumer.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.TimeoutException;


@Component
public class FanoutConsumer {

    //消息发送到交换机 再按照规则路由到与之有绑定关系的队列  所有的队列
    @RabbitListener(queues = "fanout.play1")//监听的队列名称 licslan-test
    public void sendMesAMQP(String mes) throws IOException, TimeoutException, InterruptedException {

        System.out.println("get mes is "+mes+"   "+ LocalTime.now());
        Thread.sleep(500);
    }

    @RabbitListener(queues = "fanout.play2")//监听的队列名称 licslan-test
    public void sendMesAMQP2(String mes) throws IOException, TimeoutException, InterruptedException {
        System.out.println("get mes is ===================>"+mes+"   "+ LocalTime.now());
        Thread.sleep(20);
    }
}
