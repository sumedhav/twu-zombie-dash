package com.zombiedash.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/attendee/1")
public class AttendeeController {
        public AttendeeController() {
        }

        @RequestMapping(value="home",method = RequestMethod.GET)
        public ModelAndView showCustomerPage() {
            ModelAndView modelAndView = new ModelAndView("attendee");
            return modelAndView;
        }

}
