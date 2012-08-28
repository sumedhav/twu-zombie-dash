package com.zombiedash.app.controller;

import com.zombiedash.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        if(isMessageSet())
                modelAndView.addObject("messageToBeDisplayed", messageToBeDisplayed);
        modelAndView.setViewName("loginform");
        resetMessageToBeDisplayed();
        return modelAndView;
    }

    @RequestMapping(value = "Authenticate", method = RequestMethod.POST)
    public ModelAndView processForm(@RequestParam("j_username") String username, @RequestParam("j_password") String password, HttpServletRequest request) {
            ModelAndView modelAndView = new ModelAndView();
            try {
                userService.authenticateAndReturnUser(username,password);
                modelAndView.addObject("username",username);
                HttpSession session = request.getSession(true);
                session.setAttribute("username", username);
                modelAndView = new ModelAndView("redirect:/zombie/login/HomePage");
            } catch (Exception e) {
                modelAndView = new ModelAndView("redirect:/zombie/login/LoginForm");
                modelAndView.addObject("errorMessage", "Unauthorized");
                messageToBeDisplayed = "You have entered an invalid Username or Password!!";
            } finally {
                return modelAndView;
            }
    }

    @RequestMapping(value = "HomePage", method = RequestMethod.GET)
    public ModelAndView redirectToHomePageIfSessionPersists() {
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("loginsuccess");
      return modelAndView;
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

    private boolean isMessageSet() {
        return !messageToBeDisplayed.equals("");
    }

    private void resetMessageToBeDisplayed(){
        messageToBeDisplayed ="";
    }
}
