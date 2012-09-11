package com.zombiedash.app.controller;

import com.zombiedash.app.forms.TaskForm;
import com.zombiedash.app.model.Task;
import com.zombiedash.app.repository.TaskRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TaskControllerTest {
    private  TaskController taskController;
    private TaskRepository taskRepository;


    @Before
    public void setUpRepositories() {
        taskRepository = Mockito.mock(TaskRepository.class);
        taskController= new TaskController(taskRepository);
    }

    @Test
    public void shouldGoToTaskCreationFormFromConferenceView() throws Exception {
        UUID conferenceId = UUID.randomUUID();
        ModelAndView modelAndView = taskController.showTaskCreationForm("" + conferenceId);
        MatcherAssert.assertThat(modelAndView.getViewName(), Matchers.is("createtask"));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        TaskForm taskForm = new TaskForm();
        taskForm.setTaskName("Spring");
        taskForm.setTaskDescription("Yellow Fellow");
        UUID conferenceId = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();
        when(taskRepository.insertTask(any(Task.class))).thenReturn(taskId);
        ModelAndView modelAndView = taskController.createTask("" + conferenceId, taskForm);
        MatcherAssert.assertThat(modelAndView.getViewName(), Matchers.is(Matchers.equalTo("redirect:/zombie/admin/task/" + taskId + "/create/question")));
    }

    @Test
    public void shouldStayOnCreateTaskFormIfAnyFieldLeftEmpty() throws Exception {
        UUID conferenceId = UUID.randomUUID();
        TaskForm taskForm = new TaskForm();
        taskForm.setTaskName("  ");
        taskForm.setTaskDescription("Yellow Fellow");
        ModelAndView modelAndView = taskController.createTask("" + conferenceId, taskForm);
        Map<String, String> model = ((Map<String, String>) modelAndView.getModel().get("model"));
        MatcherAssert.assertThat(model.get("description"), Matchers.is(Matchers.equalTo("Yellow Fellow")));
        MatcherAssert.assertThat(modelAndView.getViewName(), Matchers.is(Matchers.equalTo("createtask")));
    }

}