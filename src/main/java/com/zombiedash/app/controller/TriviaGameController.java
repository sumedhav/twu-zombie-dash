package com.zombiedash.app.controller;

import com.zombiedash.app.repository.QuestionRepository;
import com.zombiedash.app.repository.ResultRepository;
import com.zombiedash.app.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/attendee/game")
public class TriviaGameController {
    private ResultService resultService;

    @Autowired
    public TriviaGameController(ResultService resultService) {
        this.resultService = resultService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showGamePage() {
        ModelAndView modelAndView = new ModelAndView("triviagamepage");
        modelAndView.addObject("questions", resultService.listQuestions());
        return modelAndView;
    }

    @RequestMapping(value = "result", method = RequestMethod.POST)
    public ModelAndView showResultsPage(@RequestParam Map<String, String> params, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("redirect:/zombie/attendee/1/home");
        int obtainedScore = resultService.getScoreOfUserSelectedOptions(params);
        String userName = principal.getName();
        System.out.println(userName);
        //TODO: replace the parameters
        resultService.addCompletedTask(userName, UUID.randomUUID(),obtainedScore);
        return modelAndView;
    }
}