package com.lin.licslan.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author licslan
 */
@Configuration
public class FanoutRabbitConfig {
    //声明交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutPlay");
    }

    //声明一个队列
    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanout.play1");
    }

    //声明一个队列
    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanout.play2");
    }

    //绑定交换机
    @Bean
    public Binding bindingQueue1(Queue fanoutQueue1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }

    //绑定交换机
    @Bean
    public Binding bindingQueue2(Queue fanoutQueue2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }

}
