package com.zombiedash.app.model;

import java.util.UUID;

public class Option {
    private UUID optionId;
    private UUID questionId;
    private String text;
    private boolean isCorrect;

    public Option(UUID optionId, String text, Boolean correct, UUID questionId) {
        this.optionId = optionId;
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

    public UUID getQuestionId() {
        return questionId;
    }

    public UUID getOptionId() {
        return optionId;
    }
}
