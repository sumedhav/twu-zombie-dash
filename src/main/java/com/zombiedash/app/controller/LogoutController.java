package com.zombiedash.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/logout")
public class LogoutController {
    public ModelAndView logout() {
        ModelAndView modelAndView = new ModelAndView("loginform");
        modelAndView.addObject("messageToBeDisplayed", "Successful logout");
        return modelAndView;
    }
}
