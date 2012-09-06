package com.zombiedash.app.model;

import java.util.UUID;

public class Option {
    private UUID questionId;
    private String text;
    private boolean isCorrect;

    public Option( UUID questionId, String text, Boolean correct) {
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
