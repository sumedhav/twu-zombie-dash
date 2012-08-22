package com.zombiedash.app.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin/listusers")
public class ListUsersController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView listUsers() {
        List<String> list = new ArrayList<String>();
        list.add("User 1");
        list.add("User 2");
        return new ModelAndView("listusers","Users", list);
    }
}