package com.zombiedash.app.controller;

import com.zombiedash.app.repository.ResultRepository;
import com.zombiedash.app.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/attendee/1")
public class AttendeeController {
        private ResultService resultService;
        private ResultRepository resultRepository;

    @Autowired
    public AttendeeController(ResultService resultService,ResultRepository resultRepository) {
        this.resultService = resultService;
        this.resultRepository=resultRepository;
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public ModelAndView display(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("attendee");
        String username = principal.getName();
        int score = resultService.getAttendeeScore(username);
        modelAndView.addObject("obtainedScore", score);
        modelAndView.addObject("incompleteTasks",resultRepository.getIncompleteTasks(principal.getName()));
        return modelAndView;
    }
}
