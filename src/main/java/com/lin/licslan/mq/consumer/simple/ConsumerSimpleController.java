package com.lin.licslan.mq.consumer.simple;

import com.rabbitmq.client.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author licslan
 * */
@RestController
@Component
public class ConsumerSimpleController {

    //原始API 写法 消费消息
    @GetMapping("/simpleEat")
    public String sendMes() throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.58.128");
        factory.setPort(8074);
        factory.setPassword("admin");
        factory.setUsername("admin");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        //创建连接通道channel
        Channel channel = connection.createChannel();
        String name = "LICSLAN";
        channel.queueDeclare(name,false,false,false,null);

        //异步处理
        channel.basicConsume(name,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                //super.handleDelivery(consumerTag, envelope, properties, body);
                String mes = new String(body);
                System.out.println("get mes "+mes);
            }
        });
        System.out.println("print mes.............. :");

        return "simple ok!";
    }


    //改造成Spring AMQP 发送和接收


//    @RabbitListener(queues = "LICSLAN")
//    public void sendMesAMQP(String mes) throws IOException, TimeoutException {
//
//        System.out.println("get mes isxxxxxxxxxxxxx "+mes);
//    }
}
