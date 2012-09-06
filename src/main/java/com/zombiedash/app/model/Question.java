package com.zombiedash.app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Question {
    private UUID questionId;
    private String text;

    private List<Option> options =new ArrayList<Option>();

    public Question(UUID questionId, String text, List<Option> options) {
        this.questionId = questionId;
        this.text = text;
        this.options =options;
    }

    public String getText() {
        return text;
    }

   public List<Option> getOptions() {
        return options;
    }

    public String getValidOption() {
        String answer = "";
        for (Option option : options){
            if(option.isCorrect()){
                answer = option.getText();
                break;
            }
        }
        return answer;
    }
}

