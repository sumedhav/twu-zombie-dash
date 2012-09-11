package com.zombiedash.app.forms;


import com.zombiedash.app.model.Task;

import java.util.HashMap;
import java.util.UUID;

public class TaskForm {
    private String task_name;
    private String task_description;

    private HashMap<String, String> model = new HashMap<String, String>();

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public Task createTask(UUID conferenceId) {

        return new Task(task_name,
                    UUID.randomUUID(),
                    task_description,
                    conferenceId);
    }

    public HashMap<String, String> populateModelMapWithFormValues() {
        model.put("name",task_name);
        model.put("description",task_description);
        return model;
    }

    public boolean isValidData() {
        task_name = task_name.trim();
        task_description = task_description.trim();

        boolean validDataFlag = this.isCompletedField(task_name, "tasknameFieldEmpty");
        validDataFlag  &= this.isCompletedField(task_description, "descriptionFieldMissing");

        return validDataFlag;
    }

    private boolean isCompletedField(String field, String fieldMissingErrorName) {
        if (field.isEmpty()) {
            model.put(fieldMissingErrorName,"You can't leave this field empty.");
            return false;
        }
        return true;
    }

}
