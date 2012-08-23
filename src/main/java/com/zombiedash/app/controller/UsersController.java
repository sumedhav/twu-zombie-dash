package com.zombiedash.app.controller;

import java.util.List;

import com.zombiedash.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin/users/")
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "")
    public ModelAndView listUsers() {
        return new ModelAndView("listusers","Users", userService.getAllUsers());
    }

    @RequestMapping(value = "create/")
    public ModelAndView createUser() {
        return new ModelAndView("createuser");
    }

    @RequestMapping(value = "create/submit/", method = RequestMethod.POST)
    public ModelAndView createUserSubmit(@RequestParam("username") String username,
                                   @RequestParam("password") String password) {
        userService.createUser(username, password);
        ModelAndView modelAndView = new ModelAndView("listusers", "Users", userService.getAllUsers());
        return modelAndView;
    }

}