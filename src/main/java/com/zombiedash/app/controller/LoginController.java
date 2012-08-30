package com.zombiedash.app.controller;

import com.zombiedash.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/login")


public class LoginController {
    private UserService userService;
    private String messageToBeDisplayed = new String("");

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "LoginForm", method = RequestMethod.GET)
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("loginform");
        return modelAndView;
    }
    @RequestMapping(value = "HomePage", method = RequestMethod.GET)
    public ModelAndView redirectToHomePageIfSessionPersists() {
      return new ModelAndView("loginsuccess");
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView showLoginPageWithLogoutMessage () {
        ModelAndView modelAndView = new ModelAndView("loginform");
        modelAndView.addObject("messageToBeDisplayed", "Successful logout");
        return modelAndView;
    }


}
