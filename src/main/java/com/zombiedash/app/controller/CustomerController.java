package com.zombiedash.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/conference/user")
public class CustomerController {
        public CustomerController() {
        }

        @RequestMapping(value="home",method = RequestMethod.GET)
        public ModelAndView showCustomerPage() {
            ModelAndView modelAndView = new ModelAndView("customer");
            return modelAndView;
        }
}
