package com.zombiedash.app.controller;

import com.zombiedash.app.forms.QuestionForm;
import com.zombiedash.app.forms.TaskForm;
import com.zombiedash.app.model.Question;
import com.zombiedash.app.model.Task;
import com.zombiedash.app.repository.ConferenceRepository;
import com.zombiedash.app.repository.QuestionRepository;
import com.zombiedash.app.repository.TaskRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TaskControllerTest {
  private  TaskController taskController;
  private TaskRepository taskRepository;
  private QuestionRepository questionRepository;
  private ConferenceRepository conferenceRepository;

  @Before
  public void setUpRepositories() {
    taskRepository = Mockito.mock(TaskRepository.class);
    questionRepository = mock(QuestionRepository.class);
    conferenceRepository = mock(ConferenceRepository.class);
    taskController= new TaskController(taskRepository, questionRepository,conferenceRepository);
  }

  @Test
  public void shouldGoToTaskCreationFormFromConferenceView() throws Exception {
    UUID conferenceId = UUID.randomUUID();
    ModelAndView modelAndView = taskController.showTaskCreationForm("" + conferenceId);
    MatcherAssert.assertThat(modelAndView.getViewName(), Matchers.is("createtask"));
    assertThat((String) modelAndView.getModelMap().get("conferenceId"),is(equalTo(conferenceId.toString())));
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
    assertThat(modelAndView.getViewName(), is("redirect:/zombie/gamedesigner/task/" + taskId + "/create/question"));
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
    Question question = mock(Question.class);
    UUID taskId = UUID.randomUUID();
    when(questionForm.isValidData()).thenReturn(true);
    when(questionForm.createQuestion(taskId)).thenReturn(question);
    ModelAndView modelAndView = taskController.createQuestion(taskId.toString(), questionForm);
    verify(questionRepository).insertQuestion(question);
    assertThat(modelAndView.getViewName(),is(equalTo("redirect:/zombie/gamedesigner/conference/null")));
  }

  @Test
  public void shouldGoToHomePage() {
    Principal principal = mock(Principal.class);
    ModelAndView homePage = taskController.showHomePage(principal);
    assertThat(homePage.getViewName(),is(equalTo("gamedesignerhome")));
  }

  @Test
  public void shouldShowErrorPageOnExceptionalCircumstances() {
    TaskForm taskForm = mock(TaskForm.class);
    when(taskForm.isValidData()).thenThrow(new NullPointerException());
    ModelAndView errorPage = taskController.createTask(null,taskForm);
    assertThat(errorPage.getViewName(),is(equalTo("generalerrorpage")));
  }

  @Test
  public void shouldAddAnotherQuestion() {
    String taskId = UUID.randomUUID().toString();
    QuestionForm questionForm = mock(QuestionForm.class);
    when(questionForm.isValidData()).thenReturn(true);
    when(questionForm.getAddAnotherQuestion()).thenReturn(true);
    ModelAndView modelAndView = taskController.createQuestion(taskId,questionForm);
    assertThat(modelAndView.getViewName(),is(equalTo("redirect:/zombie/gamedesigner/task/"+ taskId +"/create/question")));
  }

  @Test
  public void shouldStayOnCreateQuestionPageIfDataIsInvalid() {
    QuestionForm questionForm = mock(QuestionForm.class);
    when(questionForm.isValidData()).thenReturn(false);
    ModelAndView modelAndView = taskController.createQuestion("", questionForm);
    assertThat(modelAndView.getViewName(), is(equalTo("createquestion")));
  }

}