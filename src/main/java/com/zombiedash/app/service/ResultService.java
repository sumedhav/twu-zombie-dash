package com.zombiedash.app.service;

import com.zombiedash.app.model.Question;
import com.zombiedash.app.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        for(Question question: questionRepository.fetchAllQuestions()){
           if(question.getValidOption().equals(userAnswers.get(currentAnswer))){

               score+=POINTS_FOR_CORRECT_ANSWER;
           }
            currentAnswer++;
        }
        return score;
    }

    public int getScoreOfUserSelectedOptions(Map<String, String> params) {
        int noOfQuestionsInRepository = questionRepository.listAllQuestions().size();
        List<String> userAnswers=new ArrayList<String>();
        int question_id;
        for(question_id=1;question_id<=noOfQuestionsInRepository;question_id++){
            userAnswers.add(params.get("question_"+question_id));
        }
       return  calculateScore(userAnswers);
    }


}
