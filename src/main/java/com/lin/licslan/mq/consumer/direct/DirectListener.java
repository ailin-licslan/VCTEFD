package com.lin.licslan.mq.consumer.direct;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author licslan
 * */
@Component
public class DirectListener {

    @RabbitListener(bindings = @QueueBinding(
            //队列2
            value = @Queue(name = "direct-queue1"),
            //交换机
            exchange = @Exchange(name = "direct-exchage",type = ExchangeTypes.DIRECT),
            //binding key
            key = {"book","game"}
    ))
    public void listenDirect(String mes){
        System.out.println("Queue1===============>"+mes);
    }



    @RabbitListener(bindings = @QueueBinding(
            //队列2
            value = @Queue(name = "direct-queue2"),
            //交换机
            exchange = @Exchange(name = "direct-exchage",type = ExchangeTypes.DIRECT),
            //binding key
            key = {"book","english"}
    ))
    public void listenDirect2(String mes){
        System.out.println("Queue2===============>"+mes);
    }
}
