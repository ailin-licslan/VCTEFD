package com.lin.licslan.mq.consumer.topic;

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
public class TopicListener {

    @RabbitListener(bindings = @QueueBinding(
            //队列2
            value = @Queue(name = "topic-queue1"),
            //交换机
            exchange = @Exchange(name = "topic-exchage",type = ExchangeTypes.TOPIC),
            //binding key
            key = {"book.#"}
    ))
    public void listenTopic(String mes){
        System.out.println("Queue1===============>"+mes);
    }



    @RabbitListener(bindings = @QueueBinding(
            //队列2
            value = @Queue(name = "topic-queue2"),
            //交换机
            exchange = @Exchange(name = "topic-exchage",type = ExchangeTypes.TOPIC),
            //binding key
            key = {"#.book","english"}
    ))
    public void listenTopic2(String mes){
        System.out.println("Queue2===============>"+mes);
    }
}
