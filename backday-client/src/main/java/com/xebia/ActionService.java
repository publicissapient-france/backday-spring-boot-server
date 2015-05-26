package com.xebia;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ActionService {
    public static final Logger LOGGER = LoggerFactory.getLogger(Logger.class);

    @Autowired
    ActionRepository actionRepository;

    @HystrixCommand(fallbackMethod = "defaultBuy")
    public void buy(Action action) {
        Buy buy = new Buy();
        buy.setId(action.getId());
        buy.setValue(action.getValue());
        buy.setDate(new Date());

        actionRepository.save(buy);
        LOGGER.info("Save action {}", buy);
    }

    public void defaultBuy(Action action) {
        LOGGER.warn("Fallback action for {}", action);
    }

    public List<Buy> findAll() {
        return actionRepository.findAll();
    }
}
