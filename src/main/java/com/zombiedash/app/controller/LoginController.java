package com.zombiedash.app.controller;

import com.zombiedash.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



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
        if(!messageToBeDisplayed.equals(""))
                modelAndView.addObject("messageToBeDisplayed", messageToBeDisplayed);
        modelAndView.setViewName("loginform");
        messageToBeDisplayed = "";
        return modelAndView;
    }

    @RequestMapping(value = "HomePage", method = RequestMethod.GET)
    public ModelAndView redirectToHomePageIfSessionPersists() {
      return new ModelAndView("loginsuccess");
    }

    @RequestMapping(value = "Logout", method = RequestMethod.GET)
    public ModelAndView redirectToLoginFormOnClickingLogout(HttpServletRequest request ) {
        HttpSession session = request.getSession(true);
        session.invalidate();
        ModelAndView modelAndView = new ModelAndView("redirect:/zombie/login/LoginForm");
        modelAndView.addObject("messageToBeDisplayed", "Logout");
        messageToBeDisplayed = "You have been logged out successfully!!";
        return modelAndView;
    }
}
