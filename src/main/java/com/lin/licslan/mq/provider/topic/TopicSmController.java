package com.lin.licslan.mq.provider.topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author licslan
 * */
@RestController
public class TopicSmController {

    //路由模式  每个queue和exchange设置一个bindingkey  发送者发送消息时指定routingkey  exchange 将消息路由到到bindingkey与消息routingkey一致的队列

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法




    @GetMapping("/topic")
    public String sendMesAMQP() throws IOException, TimeoutException {
        String topicName= "topic-exchage";
        String mes = "send to book binding key pls";
        String mes2 = "send to china.book binding key pls===============>";
        //rabbitTemplate.convertAndSend(exchangeName,"english",mes);
        rabbitTemplate.convertAndSend(topicName,"china.book",mes2);
        rabbitTemplate.convertAndSend(topicName,"book.china",mes);
        return "topic!";
    }


}
