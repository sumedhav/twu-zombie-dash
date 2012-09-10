package com.zombiedash.app.controller;

import com.zombiedash.app.repository.QuestionRepository;
import com.zombiedash.app.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/attendee/1")
public class AttendeeController {
    private QuestionRepository questionRepository;
    private ResultService resultService;

    @Autowired
    public AttendeeController(QuestionRepository questionRepository, ResultService resultService) {
        this.questionRepository = questionRepository;
        this.resultService = resultService;
    }

    @RequestMapping(value="home")
    public ModelAndView display(@RequestParam Map<String, String> params) {
        ModelAndView modelAndView = new ModelAndView("attendee");
        int noOfQuestionsInRepository = questionRepository.listAllQuestions().size();
        List<String> userAnswers=new ArrayList<String>();
        int question_id;
        for(question_id=1;question_id<=noOfQuestionsInRepository;question_id++){
            userAnswers.add(params.get("question_"+question_id));
        }
           modelAndView.addObject("maxScore", userAnswers.size());
           modelAndView.addObject("obtainedScore",resultService.calculateScore(userAnswers));
           return modelAndView;
        }
}
