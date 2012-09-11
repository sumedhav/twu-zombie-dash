package com.zombiedash.app.controller;

import com.zombiedash.app.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/attendee/1")
public class AttendeeController {
    private ResultService resultService;

    @Autowired
    public AttendeeController(ResultService resultService) {
        this.resultService = resultService;
    }

    @RequestMapping(value="home")
    public ModelAndView display(@RequestParam Map<String, String> params) {
        ModelAndView modelAndView = new ModelAndView("attendee");
        modelAndView.addObject("obtainedScore", resultService.getScoreOfUserSelectedOptions(params));
        return modelAndView;
        }
}
