package com.zombiedash.app.service;

import com.zombiedash.app.model.Question;
import com.zombiedash.app.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    private QuestionRepository questionRepository;
    private int POINTS_FOR_CORRECT_ANSWER=1;

    @Autowired
    public ResultService(QuestionRepository questionRepository) {
        this.questionRepository=questionRepository;
    }

    public int calculateScore(List<String> userAnswers) {
        int score=0,currentAnswer=0;
        for(Question question: questionRepository.listAllQuestions()){
           if(question.getValidOption().equals(userAnswers.get(currentAnswer))){

               score+=POINTS_FOR_CORRECT_ANSWER;
           }
            currentAnswer++;
        }
        return score;
    }
}
