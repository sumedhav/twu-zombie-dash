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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/admin")
public class UsersController {

    private UserService userService;
    private ValidationMessagesMap validationMessagesMap;

    @Autowired
    public UsersController(UserService userService, ValidationMessagesMap validationMessagesMap) {
        this.userService = userService;
        this.validationMessagesMap = validationMessagesMap;
    }

    @RequestMapping(value = "/users-management")
    public ModelAndView listUsers() {
        return new ModelAndView("listusers", "Users", userService.getAllUsers());
    }

    @RequestMapping(value = "/user/create", method = GET)
    public ModelAndView createUser() {
        return new ModelAndView("createuser");
    }

    @RequestMapping(value = "/user/create", method = POST)
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
                modelAndView = new ModelAndView("redirect:/zombie/admin/users-management");
            } catch (IllegalArgumentException exception) {
                modelAndView = new ModelAndView("createuser");
                modelAndView.addObject("model", userForm.populateFormValuesIntoMap());
                modelAndView.addObject("validationMessage", validationMessagesMap.getMessageFor(exception.getMessage()));
            } catch (Exception e) {
                modelAndView = new ModelAndView("errorpage");
            }
        }
        return modelAndView;
    }

    private void populateErrorsIntoModelAndView(ModelAndView modelAndView, List<String> errorCodesList) {
        for (String errorCode : errorCodesList) {
            modelAndView.addObject(errorCode, validationMessagesMap.getMessageFor(errorCode));
        }

    }

    @RequestMapping(value = "/user/error", method = GET)
    public ModelAndView showErrorPage() {
        return new ModelAndView("errorpage");
    }

    @RequestMapping(value = "/user/{userName}")
    public ModelAndView showUserDetails(@PathVariable("userName") String userName) {
        User user = userService.getUser(userName);
        Map<String,String> map = new HashMap<String,String>();
        map.put("userName",user.getUserName());
        map.put("name",user.getName());
        map.put("role",user.getRole().toString());
        map.put("email",user.getEmail());
        return new ModelAndView("userdetails", "User", map);
    }

    @RequestMapping(value = "/deleteuser/{username}", method = RequestMethod.GET)
    public ModelAndView processDeleteUser(@PathVariable("username") String username) {
        ModelAndView modelAndView;

        try {
            userService.deleteUser(username);
            modelAndView = new ModelAndView("redirect:/zombie/admin/users-management");
        } catch (Exception e) {
            modelAndView = new ModelAndView("redirect:/zombie/admin/user/error");
        }

        return modelAndView;
    }
}