package com.lin.licslan.mq.provider.fanout;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author licslan
 * */
@RestController
public class FanoutController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/fanout")
    public String sendMesAMQP() throws IOException, TimeoutException {
        String exchangeName= "fanoutPlay";
        String mes = "use AMQP and exchange send mes to me!";
        rabbitTemplate.convertAndSend(exchangeName,"",mes);
        return "exchange!";
    }
}
