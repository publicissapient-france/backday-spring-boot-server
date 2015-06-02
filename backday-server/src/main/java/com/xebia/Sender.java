package com.xebia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class Sender {

    public static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Autowired
    ActionRepository actionRepository;

    @Autowired RabbitTemplate rabbitTemplate;

    @HystrixCommand(fallbackMethod = "defaultSendAction")
    public void sendAction(Action action) {
        rabbitTemplate.convertAndSend(ServerConfiguration.queueName, action);
        LOGGER.info("Sending message " + action);
    }

    @HystrixCommand
    public void defaultSendAction(Action action) {
        LOGGER.warn("Fallback for action " + action);
        actionRepository.save(action);
    }


}
