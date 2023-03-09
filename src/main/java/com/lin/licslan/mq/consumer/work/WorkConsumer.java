package com.lin.licslan.mq.consumer.work;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author licslan
 * */

@Component
public class WorkConsumer {

    //多个消费者去竞争  阅后即焚   不重复  注意mq的预取机制
    @RabbitListener(queues = "LICSLAN")//监听的队列名称 licslan-test
    public void sendMesAMQP(String mes) throws IOException, TimeoutException, InterruptedException {

        System.out.println("get mes is "+mes+"   "+ LocalTime.now());
        Thread.sleep(500);
    }

    @RabbitListener(queues = "LICSLAN")//监听的队列名称 licslan-test
    public void sendMesAMQP2(String mes) throws IOException, TimeoutException, InterruptedException {
        System.out.println("get mes is ===================>"+mes+"   "+ LocalTime.now());
        Thread.sleep(20);
    }
}
