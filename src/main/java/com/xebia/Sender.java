package com.xebia;

import java.util.Random;
import java.util.UUID;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class Sender {

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

    @Scheduled(cron = "0/10 * * * * *")
    @HystrixCommand(fallbackMethod = "displayActions")
    public void sendActions() {
        Action action = new Action(UUID.randomUUID().toString(), new Random().nextLong());
        System.out.println("Sending message : " + action);

        rabbitTemplate.convertAndSend(queueName, action);
    }

    public void displayActions() {
        Action action = new Action(UUID.randomUUID().toString(), Math.abs(new Random().nextLong()));
        System.out.println("Save message locally : " + action);

        actionRepository.save(action);
    }


}
