package com.zombiedash.app.controller;

import com.zombiedash.app.repository.AttendeeScoreRepository;
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
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/attendee/task")
public class TriviaGameController {
    private ResultService resultService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TriviaGameController(ResultService resultService) {
        this.resultService = resultService;
    }

    @RequestMapping(value = "{taskId}",method = RequestMethod.GET)
    public ModelAndView showGamePage(@PathVariable String taskId, Principal principal) {
        String username = principal.getName();
        ModelAndView modelAndView = new ModelAndView();
        if(resultService.isTaskComplete(username,taskId))   {
            modelAndView.setViewName("generalerrorpage");
            modelAndView.addObject("taskAlreadyComplete","This task has already been performed.");
            modelAndView.addObject("urlToReturnTo","/zombie/attendee/home");
            modelAndView.addObject("returnToPrevPageMessage","Go back to home page");
            return modelAndView;
        }
        modelAndView.setViewName("triviagamepage");
        modelAndView.addObject("questions", resultService.listQuestions(taskId));
        modelAndView.addObject("incompleteTaskId",taskId);
        return modelAndView;
    }

    @RequestMapping(value = "{taskId}", method = RequestMethod.POST)
    public ModelAndView showResultsPage(@RequestParam Map<String, String> params,@PathVariable String taskId,Principal principal) {
        try {
            ModelAndView modelAndView = new ModelAndView("redirect:/zombie/attendee/home");
            String userName = principal.getName();
            UUID currentTaskId = UUID.fromString(taskId);
            AttendeeScoreRepository attendeeScoreRepository = new AttendeeScoreRepository(jdbcTemplate);
            attendeeScoreRepository.insertAnswers(userName, currentTaskId, params);
            resultService.calculateScore(userName, currentTaskId);
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("generalerrorpage");
            modelAndView.addObject("errorMessage",e.getMessage());
            modelAndView.addObject("urlToReturnTo","/zombie/attendee/home");
            modelAndView.addObject("returnToPrevPageMessage","Go back to home page");
            return modelAndView;
        }
    }
}