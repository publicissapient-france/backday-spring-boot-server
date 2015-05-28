package com.xebia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyController {

    @Autowired
    BuyRepository buyRepository;

    @RequestMapping(value="/buy.html", method = RequestMethod.GET)
    public Model getInfoBuys(Model model) {
        model.addAttribute("buys", buyRepository.findAll());
        model.addAttribute("count", buyRepository.count());
        return model;
    }
}
