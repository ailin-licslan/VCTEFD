package com.lin.licslan.mq.provider.fanout;

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
public class FanoutController {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * Fanout Exchange: This exchange type routes messages to all the queues that are bound to it.
     * It doesn't take the routing key into account, so all messages are broadcast to all queues.
     * When a message is sent to a fanout exchange, it's immediately distributed to all the bound queues.
     * This exchange type is useful when you need to send the same message to multiple consumers simultaneously,
     * such as in a publish/subscribe model.
     */
    @GetMapping("/fanout")
    public String sendMesAMQP() throws IOException, TimeoutException {
        String exchangeName = "fanoutPlay";
        String mes = "use AMQP and exchange send mes to me!";
        rabbitTemplate.convertAndSend(exchangeName, "", mes);
        return "exchange!";
    }
}
