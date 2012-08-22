package com.zombiedash.app.model;

public class Option {
    private int questionId;
    private String text;
    private boolean isCorrect;

    public Option( int questionId, String text, Boolean correct) {
        this.questionId = questionId;
        this.text = text;
        this.isCorrect = correct;
    }

    public String getText() {
        return text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
