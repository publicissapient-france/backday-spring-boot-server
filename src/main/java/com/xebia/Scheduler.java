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

    @Scheduled(cron = "0/20 * * * * *")
    public void sendActions() {
        Action action = new Action(UUID.randomUUID().toString(), new Random().nextLong());

        sender.sendAction(action);
    }

}
