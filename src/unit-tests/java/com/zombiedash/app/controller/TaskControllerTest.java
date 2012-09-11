package com.zombiedash.app.controller;

import com.zombiedash.app.forms.TaskForm;
import com.zombiedash.app.model.Conference;
import com.zombiedash.app.repository.ConferenceRepository;
import com.zombiedash.app.repository.TaskRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

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
        taskForm.setTask_name("Spring");
        taskForm.setTask_description("Yellow Fellow");
        UUID conferenceId = UUID.randomUUID();
        ModelAndView modelAndView = taskController.createTask("" + conferenceId, taskForm);
        MatcherAssert.assertThat(modelAndView.getViewName(), Matchers.is(Matchers.equalTo("redirect:/zombie/admin/conference/" + conferenceId + "/create/question")));
    }

    @Test
    public void shouldStayOnCreateTaskFormIfAnyFieldLeftEmpty() throws Exception {
        UUID conferenceId = UUID.randomUUID();
        TaskForm taskForm = new TaskForm();
        taskForm.setTask_name("  ");
        taskForm.setTask_description("Yellow Fellow");
        ModelAndView modelAndView = taskController.createTask("" + conferenceId, taskForm);
        Map<String, String> model = ((Map<String, String>) modelAndView.getModel().get("model"));
        MatcherAssert.assertThat(model.get("description"), Matchers.is(Matchers.equalTo("Yellow Fellow")));
        MatcherAssert.assertThat(modelAndView.getViewName(), Matchers.is(Matchers.equalTo("createtask")));

    }
}