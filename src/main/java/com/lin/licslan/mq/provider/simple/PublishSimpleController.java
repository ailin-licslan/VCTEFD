package com.lin.licslan.mq.provider.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author licslan
 */
@RestController
public class PublishSimpleController {

    @GetMapping("/simpleSend")
    public String sendMes() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.58.128");
        factory.setPort(8074);
        factory.setPassword("admin");
        factory.setUsername("admin");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String name = "LICSLAN";
        String mes = "hello rabbitmq! from simple model";
        channel.queueDeclare(name, false, false, false, null);
        channel.basicPublish("", name, null, mes.getBytes());
        System.out.println("send mes :" + mes);
        channel.close();
        connection.close();
        return "simple ok!";
    }


    //改造成Spring AMQP 发送和接收

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * Simple Queue Model is a messaging pattern used to implement a one-to-one communication between a
     * producer (or sender) and a consumer (or receiver). In this model, the producer sends messages to
     * a queue, and the consumer receives messages from the queue in the order they were sent. The producer
     * and consumer can be implemented on the same or different machines.
     * <p>
     * In Simple Queue Model, the producer sends a message to a specific queue, and the consumer receives
     * messages from the same queue. This model is useful when there is only one consumer for a specific queue,
     * and the order of messages is important. The Simple Queue Model is commonly used in systems that require
     * reliable message delivery and where messages need to be processed in a specific order.
     */

    @GetMapping("/simpleSendAMQP")
    public String sendMesAMQP() throws IOException, TimeoutException {
        String queueNmae = "LICSLAN";
        String mes = "use AMQP send mes to me!";
        rabbitTemplate.convertAndSend(queueNmae, mes);
        return "AMQP";
    }
}
