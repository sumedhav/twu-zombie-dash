package com.zombiedash.app.controller;

import com.zombiedash.app.repository.QuestionRepository;
import com.zombiedash.app.repository.ResultRepository;
import com.zombiedash.app.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/attendee/task")
public class TriviaGameController {
    private ResultService resultService;

    @Autowired
    public TriviaGameController(ResultService resultService) {
        this.resultService = resultService;
    }

    @RequestMapping(value = "{incompleteTaskId}",method = RequestMethod.GET)
    public ModelAndView showGamePage(@PathVariable String incompleteTaskId) {
        ModelAndView modelAndView = new ModelAndView("triviagamepage");
        modelAndView.addObject("questions", resultService.listQuestions(incompleteTaskId));
        modelAndView.addObject("incompleteTaskId",incompleteTaskId);
        return modelAndView;
    }

    @RequestMapping(value = "{incompleteTaskId}", method = RequestMethod.POST)
    public ModelAndView showResultsPage(@RequestParam Map<String, String> params,@PathVariable String incompleteTaskId,Principal principal) {
        ModelAndView modelAndView = new ModelAndView("redirect:/zombie/attendee/home");
        int obtainedScore = resultService.getScoreOfUserSelectedOptions(params,incompleteTaskId);
        String userName = principal.getName();
        UUID taskId = UUID.fromString(incompleteTaskId);
        resultService.addCompletedTask(userName, taskId,obtainedScore);
        return modelAndView;
    }
}