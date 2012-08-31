package com.zombiedash.app.controller;

import com.zombiedash.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
    private UserService userService;
    private String messageToBeDisplayed = new String("");

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("loginform");
        return modelAndView;
    }
    @RequestMapping(value = "admin/home", method = RequestMethod.GET)
    public ModelAndView redirectToHomePageIfSessionPersists() {
      return new ModelAndView("loginsuccess");
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView showLoginPageWithLogoutMessage () {
        ModelAndView modelAndView = new ModelAndView("loginform");
        modelAndView.addObject("messageToBeDisplayed", "You have logged out successfully");
        return modelAndView;
    }

    @RequestMapping(value = "fail", method = RequestMethod.GET)
    public ModelAndView showLoginFailPage () {
        ModelAndView modelAndView = new ModelAndView("loginform");
        modelAndView.addObject("messageToBeDisplayed", "The username or password you entered is incorrect");
        return modelAndView;
    }

}
