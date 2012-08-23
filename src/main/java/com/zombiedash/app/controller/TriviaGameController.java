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
@RequestMapping("/conference/user/game")
public class TriviaGameController {
    private QuestionRepository questionRepository;
    private ResultService resultService;

    @Autowired
    public TriviaGameController(QuestionRepository questionRepository, ResultService resultService) {
        this.questionRepository = questionRepository;
        this.resultService = resultService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showGamePage() {
        ModelAndView modelAndView = new ModelAndView("triviagamepage");
        modelAndView.addObject("questions", questionRepository.listAllQuestions());
        return modelAndView;
    }

    @RequestMapping(value = "result", method = RequestMethod.POST)
    public ModelAndView showResultsPage(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("result");
        List<String> userAnswers=new ArrayList<String>();
        int question_id;
        for(question_id=1;question_id<=questionRepository.listAllQuestions().size();question_id++){
            userAnswers.add(params.get("question_"+question_id));
        }

        modelAndView.addObject("score",resultService.calculateScore(userAnswers));
        return modelAndView;
}
}
