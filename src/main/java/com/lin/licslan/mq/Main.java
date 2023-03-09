package com.lin.licslan.mq;

public class Main {

    /**
     * rabbitmq 队列理解 参考 https://rabbitmq.com/getstarted.html
     * 1.简单队列模型、   一个消息只能被一个消费者消费  不重复    The simplest thing that does something
     * 2.工作队列模型     一个消息只能被一个消费者消费  不重复    Distributing tasks among workers (the competing consumers pattern)
     * 3.发布订阅模式     一个消息被被多个消费者都可以消费        Sending messages to many consumers at once
     *   3.1 Fanout (广播)     Receiving messages selectively
     *   3.2 Direct (路由)    Sending messages to many consumers at once
     *   3.3 Topic  (主题)    Receiving messages based on a pattern (topics)
     *
     *   1 & 2 没有交换机  直接基于队列完成的消息发送和消费
     *   3.发布订阅的3种和交换机有一定关系  交换机类型不一样  FanoutExchange  DirectExchange  TopicExchange
     *
     *
     *   fanout 与 direct区别  Fanout发所有有绑定关系的队列   direct 是发到 队列 bindingkey rountkey一致的才发过去
     *
     *   topic 与 direct类似 区别在于routing key必须是多个单词的列表 并且以.分割 Queue 和 exchange指定binding key时可以使用通配符
     *   #: 代指0个或者多个单词  *: 代指一个单词
     *
     * */
}
