package com.zombiedash.app.controller;

import com.zombiedash.app.model.Question;
import com.zombiedash.app.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/conference/user/game")
public class TriviaGameController {
    private QuestionRepository questionRepository;

    @Autowired
    public TriviaGameController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showGamePage() {
        ModelAndView modelAndView = new ModelAndView("triviagamepage");
        modelAndView.addObject("questions", questionRepository.listAllQuestions());
        return modelAndView;
    }
}
