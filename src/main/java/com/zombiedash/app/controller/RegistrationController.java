package com.zombiedash.app.controller;

import com.zombiedash.app.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {


    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @RequestMapping("/register/{confId}")
    public ModelAndView openRegistrationPage(@PathVariable("confId") long confId) {
        if (registrationService.validateConferenceId(confId))
            return new ModelAndView("attendeeregistration");
        else
            return new ModelAndView("");

    }
}
