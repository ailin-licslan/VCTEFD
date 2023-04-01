package com.lin.licslan.mq.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
/**
 * @author licslan
 */
@Component
public class ConverterJson {

    //序列化支持rabbitmq消费JSON格式的数据
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
