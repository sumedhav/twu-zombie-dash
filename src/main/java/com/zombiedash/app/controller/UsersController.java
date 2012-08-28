package com.zombiedash.app.controller;

import com.zombiedash.app.error.ValidationMessagesMap;
import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import com.zombiedash.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/admin/users")
public class UsersController {

    private UserService userService;
    private ValidationMessagesMap validationMessagesMap;

    @Autowired
    public UsersController(UserService userService, ValidationMessagesMap validationMessagesMap) {
        this.userService = userService;
        this.validationMessagesMap = validationMessagesMap;
    }

    @RequestMapping(value = "")
    public ModelAndView listUsers() {
        return new ModelAndView("listusers", "Users", userService.getAllUsers());
    }

    @RequestMapping(value = "/create", method = GET)
    public ModelAndView createUser() {
        return new ModelAndView("createuser");
    }

    @RequestMapping(value = "/create", method = POST)
    public ModelAndView createUser(@RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   @RequestParam("role") String role,
                                   @RequestParam("name") String name,
                                   @RequestParam("email") String email) {
        ModelAndView modelAndView;

        ModelMap modelMap  = new ModelMap();
        modelMap.put("username", username);
        modelMap.put("password", password);
        modelMap.put("role", role);
        modelMap.put("name", name);
        modelMap.put("email", email);

        try{
            User user = new User(username, password, Role.generateRole(role), name, email);
            userService.createUser(user);
            modelAndView = new ModelAndView("redirect:/zombie/admin/users");
        }
        catch (IllegalArgumentException exception){
            modelAndView = new ModelAndView("createuser");
            modelAndView.addObject("model", modelMap);
            modelAndView.addObject("validationMessage", validationMessagesMap.getMessageFor(exception.getMessage()));
        }
        catch(Exception excp){
            modelAndView = new ModelAndView("errorPage");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/display/{userName}")
    public ModelAndView showUserDetails(@PathVariable("userName") String userName) {
        return new ModelAndView("userDetails", "User", userService.getUser(userName));
    }

    @RequestMapping(value = "/deleteuser/{username}", method = RequestMethod.GET)
    public ModelAndView processDeleteUser(@PathVariable("username") String username) {
        ModelAndView modelAndView ;

        try{
            userService.deleteUser(username);
            modelAndView = new ModelAndView("redirect:/zombie/admin/users");
        }catch (Exception e){
            modelAndView = new ModelAndView("redirect:/zombie/admin/users/errorPage");
        }

        return modelAndView;
    }
}