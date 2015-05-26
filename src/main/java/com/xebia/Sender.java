package com.xebia;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    public static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    final static String queueName = "actions";

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    ActionRepository actionRepository;

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("actions-exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }

    @HystrixCommand(fallbackMethod = "defaultSendAction")
    public void sendAction(Action action) {

        rabbitTemplate.convertAndSend(queueName, action);
        LOGGER.info("Sending message " + action);
    }

    @HystrixCommand
    public void defaultSendAction(Action action) {
        LOGGER.warn("Fallback for action " + action);

        actionRepository.save(action);
    }


}
