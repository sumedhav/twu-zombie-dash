//Responsibility: Intercept requests from create user page and delegate to appropriate page
package com.zombiedash.app.controller;

import com.zombiedash.app.error.ValidationMessagesMap;
import com.zombiedash.app.forms.UserForm;
import com.zombiedash.app.model.User;
import com.zombiedash.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

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
    public ModelAndView createUser(UserForm userForm) {
        ModelAndView modelAndView;
        userForm.validate();
        if (userForm.hasErrors()) {
            modelAndView = new ModelAndView("createuser");
            modelAndView.addObject("model", userForm.populateFormValuesIntoMap());
            populateErrorsIntoModelAndView(modelAndView, userForm.getErrorCodes());
        } else {
            try {
                User user = userForm.createUser();
                userService.createUser(user, userForm.getPassword());
                modelAndView = new ModelAndView("redirect:/zombie/admin/users");
            } catch (IllegalArgumentException exception) {
                modelAndView = new ModelAndView("createuser");
                modelAndView.addObject("model", userForm.populateFormValuesIntoMap());
                modelAndView.addObject("validationMessage", validationMessagesMap.getMessageFor(exception.getMessage()));
            } catch (Exception e) {
                modelAndView = new ModelAndView("errorPage");
            }
        }
        return modelAndView;
    }

    private void populateErrorsIntoModelAndView(ModelAndView modelAndView, List<String> errorCodesList) {
        for (String errorCode : errorCodesList) {
            modelAndView.addObject(errorCode, validationMessagesMap.getMessageFor(errorCode));
        }

    }

    @RequestMapping(value = "/errorPage", method = GET)
    public ModelAndView showErrorPage() {
        return new ModelAndView("errorPage");
    }

    @RequestMapping(value = "/display/{userName}")
    public ModelAndView showUserDetails(@PathVariable("userName") String userName) {
        return new ModelAndView("userDetails", "User", userService.getUser(userName));
    }

    @RequestMapping(value = "/deleteuser/{username}", method = RequestMethod.GET)
    public ModelAndView processDeleteUser(@PathVariable("username") String username) {
        ModelAndView modelAndView;

        try {
            userService.deleteUser(username);
            modelAndView = new ModelAndView("redirect:/zombie/admin/users");
        } catch (Exception e) {
            modelAndView = new ModelAndView("redirect:/zombie/admin/users/errorPage");
        }

        return modelAndView;
    }
}