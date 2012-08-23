package com.zombiedash.app.controller;

import com.zombiedash.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")


public class LoginController {
    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "LoginForm", method = RequestMethod.GET)
    public String showForm() {
        return "loginform";
    }

    @RequestMapping(value = "Authenticate", method = RequestMethod.POST)
    public ModelAndView processForm(@RequestParam("Username") String username, @RequestParam("Password") String password, ModelAndView modelAndView) {
            try {
                userService.authenticateAndReturnUser(username,password);
                modelAndView.addObject("username",username);
                modelAndView.setViewName("loginsuccess");
            } catch (Exception e) {
                modelAndView.addObject("errorMessage", "You have entered an invalid Username or Password!!");
                modelAndView.setViewName("loginform");
            } finally {
                return modelAndView;
            }
    }
}
