package com.zombiedash.app.model;

import java.util.UUID;

public class AttendeeAnswer {
    public UUID getOptionId() {
        return optionId;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public String getUsername() {
        return username;
    }

    private String username;
    private UUID taskId;
    private UUID questionId;
    private UUID optionId;

    public AttendeeAnswer(String username, UUID taskId, UUID questionId, UUID optionId) {
        this.username = username;
        this.taskId = taskId;
        this.questionId = questionId;
        this.optionId = optionId;
    }

}
