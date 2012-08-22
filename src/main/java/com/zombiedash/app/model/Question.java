package com.zombiedash.app.model;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private int questionId;
    private String text;

    private List<Option> options =new ArrayList<Option>();

    public Question(int questionId, String text, List<Option> options) {
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
}
