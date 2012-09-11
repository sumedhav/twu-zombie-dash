package com.zombiedash.app.forms;

import com.zombiedash.app.model.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.UUID;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TaskFormTest {

    private TaskForm taskForm;

    @Before
    public void setUpTaskForm() {
        taskForm = new TaskForm();
        taskForm.setTaskName("task_1");
        taskForm.setTaskDescription("Hello World");
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
        taskForm.setTaskName("");
        assertFalse(taskForm.isValidData());
    }

    @Test
    public void shouldReturnFalseForEmptyDescriptionInTaskForm() throws Exception {
        taskForm.setTaskDescription("");
        assertFalse(taskForm.isValidData());
    }

    @Test
    public void shouldReturnTrueForCompletelyFilledTaskForm() throws Exception {
        assertTrue(taskForm.isValidData());
    }
}
