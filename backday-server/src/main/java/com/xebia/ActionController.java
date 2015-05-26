package com.xebia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionController {

    @Autowired
    ActionRepository actionRepository;

    @RequestMapping(value="/action.html", method = RequestMethod.GET)
    public Model getListActions(Model model) {
        model.addAttribute("actions", actionRepository.findAll());
        return model;
    }
}
