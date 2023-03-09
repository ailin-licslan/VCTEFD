package com.lin.licslan.mq.provider.work;

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
public class WorkController {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/work")
    public String sendMesAMQP() throws IOException, TimeoutException {
        for (int i = 0; i <50; i++) {
            String queueNmae= "LICSLAN";
            String mes = "use AMQP send mes to me!";
            rabbitTemplate.convertAndSend(queueNmae,mes);
        }
        return "AMQP10";
    }
}
