package com.zombiedash.app.model;

import java.util.List;
import java.util.Map;

public class Result {

    private List<Question> questions;
    private int POINTS_FOR_CORRECT_ANSWER=1;

    private Map<Question, String> userAnswer;

    public Result(Map<Question, String> userAnswer, List<Question> questions) {
        this.userAnswer = userAnswer;
        this.questions=questions;
    }

    public int calculateScore() {
        int score=0;
        for(Question question: questions){
           if(question.getValidOption().equals(userAnswer.get(question)))
               score+=POINTS_FOR_CORRECT_ANSWER;
        }
        return score;
    }
}
