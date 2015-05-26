package com.xebia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class Scheduler {

    @Autowired
    Sender sender;

    @Scheduled(fixedRate = 2000l)
    public void sendActions() {
        Action action = new Action(UUID.randomUUID().toString(), new Random().nextInt(Integer.MAX_VALUE));

        sender.sendAction(action);
    }

}
