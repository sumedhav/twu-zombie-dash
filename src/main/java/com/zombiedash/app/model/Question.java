package com.zombiedash.app.model;

public class Question {
    private int questionId;
    private String text;

//    private List<com.zombiedash.app.model.Option> allowedOptions =new ArrayList<com.zombiedash.app.model.Option>();

    public Question(int questionId, String text) {
        this.questionId = questionId;
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
