package com.lin.licslan.mq.provider.work;

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
public class WorkController {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * Work Queue Model, on the other hand, is a messaging pattern used to implement a one-to-many communication
     * between a producer (or sender) and multiple consumers (or receivers). In this model, the producer sends
     * messages to a queue, and the messages are consumed by one of the available consumers. Each message is
     * processed by only one consumer, and the order of messages is not guaranteed.
     * <p>
     * In Work Queue Model, the producer sends a message to a specific queue, and the messages are distributed among
     * multiple consumers. The consumers can be implemented on the same or different machines. This model is useful
     * when there are multiple consumers available, and the order of messages is not important. The Work Queue Model
     * is commonly used in systems that require parallel processing of tasks and load balancing among multiple workers.
     */

    @GetMapping("/work")
    public String sendMesAMQP() throws IOException, TimeoutException {
        for (int i = 0; i < 50; i++) {
            String queueNmae = "LICSLAN";
            String mes = "use AMQP send mes to me!";
            rabbitTemplate.convertAndSend(queueNmae, mes);
        }
        return "AMQP10";
    }
}
