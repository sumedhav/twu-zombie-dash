package com.zombiedash.app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Question {
    private UUID questionId;
    private String text;

    private List<Option> options = new ArrayList<Option>();
    private UUID taskId;

    public Question(UUID questionId, String text, List<Option> options, UUID taskId) {
        this.questionId = questionId;
        this.text = text;
        this.options = options;
        this.taskId = taskId;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public String getText() {
        return text;
    }

    public List<Option> getOptions() {
        return options;
    }

    public UUID getValidOption() {
        UUID answer = UUID.randomUUID();
        for (Option option : options) {
            if (option.isCorrect()) {
                answer = option.getOptionId();
                break;
            }
        }
        return answer;
    }
}

