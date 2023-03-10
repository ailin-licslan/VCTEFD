package com.lin.licslan.mq.provider.direct;

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
public class DirectSmController {

    //路由模式  每个queue和exchange设置一个bindingkey  发送者发送消息时指定routingkey  exchange 将消息路由到到bindingkey与消息routingkey一致的队列

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        extracted();
        return "ok";
    }


    @GetMapping("/sendDirectMessage10")
    public String sendDirectMessageFor() {

        //注意消费者预取机制  默认他们分平均消费

        for (int i = 0; i < 10; i++) {
            extracted();
        }
        return "ok";
    }

    private void extracted() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello! vvvvvvvvvv";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
    }


    @GetMapping("/direct")
    public String sendMesAMQP() throws IOException, TimeoutException {
        String exchangeName= "direct-exchage";
        String mes = "send to book binding key pls";
        //rabbitTemplate.convertAndSend(exchangeName,"english",mes);
        rabbitTemplate.convertAndSend(exchangeName,"book",mes);
        return "direct!";
    }


}
