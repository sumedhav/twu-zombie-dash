package com.zombiedash.app.model;

import java.util.UUID;

public class Task {
    private String name;
    private UUID id;
    private String description;
    private UUID conferenceId;

    public Task(String name, UUID id, String description, UUID conferenceId) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.conferenceId = conferenceId;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public UUID getConferenceId() {
        return conferenceId;
    }
}
