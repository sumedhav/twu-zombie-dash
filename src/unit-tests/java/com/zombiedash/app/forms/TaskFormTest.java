package com.zombiedash.app.forms;

import com.zombiedash.app.model.Task;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.UUID;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TaskFormTest {

  private TaskForm taskForm;

  @Before
  public void setUpTaskForm() {
    taskForm = new TaskForm();
    taskForm.setTask_name("task_1");
    taskForm.setTask_description("Hello World");
  }

  @Test
  public void shouldCreateTask() throws Exception {
    UUID conferenceId = UUID.randomUUID();
    Task task = taskForm.createTask(conferenceId);
    assertThat(task.getName(), is("task_1"));
    assertThat(task.getDescription(), is("Hello World"));
  }

  @Test
  public void shouldPopulateModelMapWithTaskFormValues() throws Exception {
    HashMap<String, String> model = taskForm.populateModelMapWithFormValues();
    assertThat(model.get("name"), is("task_1"));
    assertThat(model.get("description"), is("Hello World"));
  }

  @Test
  public void shouldReturnFalseForEmptyNameFieldInTaskForm() throws Exception {
    taskForm.setTask_name("");
    assertFalse(taskForm.isValidData());
  }

  @Test
  public void shouldReturnFalseForEmptyDescriptionInTaskForm() throws Exception {
    taskForm.setTask_description("");
    assertFalse(taskForm.isValidData());
  }

  @Test
  public void shouldReturnTrueForCompletelyFilledTaskForm() throws Exception {
    assertTrue(taskForm.isValidData());
  }

  @Test
  public void shouldReturnErrorMessageOnLongName() {
    taskForm.setTask_name(StringUtils.repeat("X",101));
    boolean isValid = taskForm.isValidData();
    assertThat(isValid,is(false));
    assertThat(taskForm.getModel().get("taskname_exceed_error"),
        is(equalTo("Trying to exceed the max number (100) of characters")));
  }

  @Test
  public void shouldReturnErrorMessageOnLongDescription() {
    taskForm.setTask_description(StringUtils.repeat("X",501));
    boolean isValid = taskForm.isValidData();
    assertThat(isValid,is(false));
    assertThat(taskForm.getModel().get("description_exceed_error"),
        is(equalTo("Trying to exceed the max number (500) of characters")));
  }
}
