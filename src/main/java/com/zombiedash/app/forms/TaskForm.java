package com.zombiedash.app.forms;


import com.zombiedash.app.model.Task;

import java.util.HashMap;
import java.util.UUID;

public class TaskForm {
    private String taskName;
    private String taskDescription;

    private HashMap<String, String> model = new HashMap<String, String>();

    public void setTaskName(String task_name) {
        this.taskName = task_name;
    }

    public void setTaskDescription(String task_description) {
        this.taskDescription = task_description;
    }

    public Task createTask(UUID conferenceId) {
        return new Task(taskName,
                    UUID.randomUUID(),
                    taskDescription,
                    conferenceId);
    }

    public HashMap<String, String> populateModelMapWithFormValues() {
        model.put("name", taskName);
        model.put("description", taskDescription);
        return model;
    }

    public boolean isValidData() {
        taskName = taskName.trim();
        taskDescription = taskDescription.trim();

        boolean validDataFlag = this.isEmpty(taskName, "tasknameFieldEmpty");
        validDataFlag  &= this.isEmpty(taskDescription, "descriptionFieldMissing");

        return validDataFlag;
    }

    private boolean isEmpty(String field, String fieldMissingErrorName) {
        if (field.isEmpty()) {
            model.put(fieldMissingErrorName,"You can't leave this field empty.");
            return false;
        }
        return true;
    }

}
