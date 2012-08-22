package com.zombiedash.app.controller;

import com.zombiedash.app.model.User;
import com.zombiedash.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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
    public String processForm(@RequestParam("Username") String username, @RequestParam("Password") String password, HttpServletRequest request) {

        User givenUser = userService.authenticateAndReturnUser(username,password);
        if(givenUser==null) {
            request.setAttribute("errorMessage", "You have entered an invalid Username or Password!!");
            return "loginform";
        }
        return "loginsuccess";
    }




}
