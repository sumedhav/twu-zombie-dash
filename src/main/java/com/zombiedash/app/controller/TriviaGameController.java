package com.zombiedash.app.controller;

import com.zombiedash.app.repository.QuestionRepository;
import com.zombiedash.app.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/attendee/1/game")
public class TriviaGameController {
    private QuestionRepository questionRepository;

    @Autowired
    public TriviaGameController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showGamePage() {
        ModelAndView modelAndView = new ModelAndView("triviagamepage");
        modelAndView.addObject("questions", questionRepository.fetchAllQuestions());
        return modelAndView;
    }

    @RequestMapping(value = "result", method = RequestMethod.POST)
    public ModelAndView showResultsPage(@RequestParam Map<String, String> params) {
        ModelAndView modelAndView = new ModelAndView("redirect:/zombie/attendee/1/home", params);
        return modelAndView;
    }
}