package com.zombiedash.app.controller;

import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import com.zombiedash.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("${pageContext.request.contextPath}/admin/users/")
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
                                         @RequestParam("password") String password,
                                         @RequestParam("role") String role,
                                         @RequestParam("name") String name,
                                         @RequestParam("email") String email) {

        User user = new User(username, password, Role.generateRole(role), name, email);
        userService.createUser(user);
        return new ModelAndView("redirect:${pageContext.request.contextPath}/zombie/admin/users/");
    }

}