package com.zombiedash.app.forms;


import com.zombiedash.app.model.Task;

import java.util.HashMap;
import java.util.UUID;

public class TaskForm {
  private static final int NAME_LENGTH_LIMIT = 100;
  private static final int DESCRIPTION_LENGTH_LIMIT = 500;

  private String task_name;
  private String task_description;

  public HashMap<String, String> getModel() {
    return model;
  }

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
    model.put("name", task_name);
    model.put("description", task_description);
    return model;
  }

  public boolean isValidData() {
    task_name = task_name.trim();
    task_description = task_description.trim();

    boolean validDataFlag = this.isEmpty(task_name, "tasknameFieldEmpty");
    validDataFlag  &= this.isEmpty(task_description, "descriptionFieldMissing");

    validDataFlag &= isLessThanMaximumLength(task_name, NAME_LENGTH_LIMIT, "taskname_exceed_error");
    validDataFlag &= isLessThanMaximumLength(task_description, DESCRIPTION_LENGTH_LIMIT, "description_exceed_error");
    return validDataFlag;
  }

  private boolean isLessThanMaximumLength(String field, int limit, String errorFieldName) {
    if(field.length()>limit){
      model.put(errorFieldName, "Trying to exceed the max number ("+limit+") of characters");
      return false;
    }
    return true;
  }

  private boolean isEmpty(String field, String fieldMissingErrorName) {
    if (field.isEmpty()) {
      model.put(fieldMissingErrorName,"You can't leave this field empty.");
      return false;
    }
    return true;
  }

}
