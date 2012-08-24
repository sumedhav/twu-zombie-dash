package com.zombiedash.app.controller;

import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import com.zombiedash.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/users")
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "")
    public ModelAndView listUsers() {
        return new ModelAndView("listusers", "Users", userService.getAllUsers());
    }

    @RequestMapping(value = "/create")
    public ModelAndView createUser() {
        return new ModelAndView("createuser");
    }

    @RequestMapping(value = "/create/submit", method = RequestMethod.POST)
    public ModelAndView createUserSubmit(@RequestParam("username") String username,
                                         @RequestParam("password") String password,
                                         @RequestParam("role") String role,
                                         @RequestParam("name") String name,
                                         @RequestParam("email") String email) {
        ModelAndView modelAndView;
        try{
            User user = new User(username, password, Role.generateRole(role), name, email);
            userService.createUser(user);
            modelAndView = new ModelAndView("redirect:/zombie/admin/users");
        }
        catch(Exception excp){
            modelAndView = new ModelAndView("redirect:/zombie/admin/users/errorPage");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/errorPage", method = RequestMethod.GET)
    public ModelAndView processError(){
        return new ModelAndView("errorPage");
    }

    @RequestMapping(value = "/{userName}")
    public ModelAndView showUserDetails(@PathVariable("userName") String userName) {
        return new ModelAndView("userDetails", "User", userService.getUser(userName));
    }
}