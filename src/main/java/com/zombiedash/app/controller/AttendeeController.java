package com.zombiedash.app.controller;

import com.zombiedash.app.repository.AttendeeScoreRepository;
import com.zombiedash.app.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/attendee")
public class AttendeeController {
    private ResultService resultService;
    private AttendeeScoreRepository attendeeScoreRepository;

    @Autowired
    public AttendeeController(ResultService resultService, AttendeeScoreRepository resultRepository) {
        this.resultService = resultService;
        this.attendeeScoreRepository = resultRepository;
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public ModelAndView display(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("attendee");
        String username = principal.getName();
        int score = resultService.getAttendeeScore(username);
        modelAndView.addObject("obtainedScore", score);
        modelAndView.addObject("username", username);
        modelAndView.addObject("incompleteTasks", attendeeScoreRepository.fetchIncompleteTasks(principal.getName()));
        return modelAndView;
    }
}
