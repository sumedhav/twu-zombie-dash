package com.zombiedash.app.controller;

import com.zombiedash.app.forms.QuestionForm;
import com.zombiedash.app.forms.TaskForm;
import com.zombiedash.app.model.Question;
import com.zombiedash.app.model.Task;
import com.zombiedash.app.repository.QuestionRepository;
import com.zombiedash.app.repository.TaskRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TaskControllerTest {
    private  TaskController taskController;
    private TaskRepository taskRepository;
    private QuestionRepository questionRepository;

    @Before
    public void setUpRepositories() {
        taskRepository = Mockito.mock(TaskRepository.class);
        questionRepository = mock(QuestionRepository.class);
        taskController= new TaskController(taskRepository, questionRepository);
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
        UUID taskId = UUID.randomUUID();
        when(taskRepository.insertTask(any(Task.class))).thenReturn(taskId);
        ModelAndView modelAndView = taskController.createTask("" + conferenceId, taskForm);
        assertThat(modelAndView.getViewName(), is("redirect:/zombie/admin/task/" + taskId + "/create/question"));
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

    @Test
    public void shouldGoToQuestionCreationPageAfterTaskCreation() throws Exception {
        UUID taskId = UUID.randomUUID();
        ModelAndView modelAndView = taskController.showQuestionCreationForm("" + taskId);
        MatcherAssert.assertThat(modelAndView.getViewName(), Matchers.is("createquestion"));
    }

    @Test
    public void shouldCreateQuestionForTask() throws Exception {
        QuestionForm questionForm = mock(QuestionForm.class);
        UUID taskId = UUID.randomUUID();
        when(questionRepository.insertQuestion(any(Question.class))).thenReturn(taskId);
        when(questionForm.isValidData()).thenReturn(true);
        ModelAndView modelAndView = taskController.createQuestion(taskId.toString(), questionForm);
        assertThat(modelAndView.getViewName(), is("redirect://zombie/attendee/game"));
    }
}