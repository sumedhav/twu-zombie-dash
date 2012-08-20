package com.example.app.web.controller;

import com.example.app.web.controller.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/login")

public class LoginController {

    @RequestMapping(value = "LoginForm", method = RequestMethod.GET)
    public String showForm(Map model) {
        LoginForm loginForm = new LoginForm();
        model.put("loginForm", loginForm);
        return "loginform";
    }

    @RequestMapping(value = "Authenticate", method = RequestMethod.POST)
    public String processForm(@RequestParam("Username") String Username, @RequestParam("Password") String Password, LoginForm loginForm,
                              Map model) {

        // Validation of user inputs
        String testUserName = "Yahya";
        String testPassWord = "password";

        loginForm = (LoginForm) model.get("loginForm");

        if (!Username.equals(testUserName) || !Password.equals(testPassWord)) {
            return "loginform";
        }

        loginForm.setUserName(Username);
        loginForm.setPassword(Password);
        model.put("loginForm", loginForm);
        return "loginSuccess";
    }
}



