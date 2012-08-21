package com.zombiedash.app.controller;

import com.zombiedash.app.model.User;
import com.zombiedash.app.model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")

public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "LoginForm", method = RequestMethod.GET)
    public String showForm() {
        return "loginform";
    }

    @RequestMapping(value = "Authenticate", method = RequestMethod.POST)
    public String processForm(@RequestParam("Username") String username, @RequestParam("Password") String password, HttpServletRequest request) {
        User givenUser = userService.createAdmin();
        try{
            givenUser.authenticate(username, password.toCharArray());
            return "loginsuccess";
        }
        catch (Exception e){
            String errorMessageForFailedLogin = "You have entered an Invalid UserName/Password!! Please Re-Enter";
            request.setAttribute("errorMessage",errorMessageForFailedLogin);
            return  "loginform";
        }
    }




}
