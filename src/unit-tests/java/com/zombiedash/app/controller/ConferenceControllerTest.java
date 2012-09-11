package com.zombiedash.app.controller;

import com.zombiedash.app.forms.TaskForm;
import com.zombiedash.app.model.Conference;
import com.zombiedash.app.repository.ConferenceRepository;
import com.zombiedash.app.forms.ConferenceForm;
import com.zombiedash.app.repository.TaskRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConferenceControllerTest {

    private ConferenceRepository conferenceRepository;
    private TaskRepository taskRepository;
    private ConferenceController conferenceController;

    @Before
    public void setUpRepositories(){
         conferenceRepository = mock(ConferenceRepository.class);
         taskRepository = mock(TaskRepository.class);
         conferenceController = new ConferenceController(conferenceRepository, taskRepository);
    }
    @Test
    public void viewShouldBeCreateConference() throws Exception {
        ModelAndView actualModel = conferenceController.showConferenceCreationForm();
        assertThat(actualModel.getViewName(), is(equalTo("createconference")));
    }

    @Test
    public void clickOnConferenceHomeShouldGoToConferenceHomePage() throws Exception {
        ModelAndView actualModel = conferenceController.home();
        assertThat(actualModel.getViewName(), is(equalTo("conferencehome")));
    }

    @Test
    public void submitShouldGoToHome() throws Exception {
        ConferenceForm conferenceForm = new ConferenceForm();
        conferenceForm.setConf_name("NotNull");
        conferenceForm.setConf_topic("NotNull");
        conferenceForm.setConf_description("NotNull");
        conferenceForm.setConf_venue("NotNull");
        conferenceForm.setConf_start_date("2013-03-04");
        conferenceForm.setConf_end_date("2013-03-04");
        conferenceForm.setConf_max_attendees("10");
        ModelAndView actualModel = conferenceController.createConference(conferenceForm);
        assertThat(actualModel.getViewName(), is(equalTo("redirect:/zombie/admin/conference/list")));
    }

    @Test
    public void submitWithIncompleteFormShouldRemainOnCreateFormWithPreviousValuesRetained() throws Exception {
        ConferenceForm conferenceForm = new ConferenceForm();
        conferenceForm.setConf_name("name1");
        conferenceForm.setConf_topic("");
        conferenceForm.setConf_description("NotNull");
        conferenceForm.setConf_venue("NotNull");
        conferenceForm.setConf_start_date("NotNull");
        conferenceForm.setConf_end_date("NotNull");
        conferenceForm.setConf_max_attendees("10");
        ModelAndView actualModel = conferenceController.createConference(conferenceForm);
        Map<String, String> model = ((Map<String, String>) actualModel.getModel().get("model"));
        assertThat(model.get("name"), is(equalTo("name1")));
        assertThat(actualModel.getViewName(), is(equalTo("createconference")));
    }


    @Test
    public void shouldDisplayNoConferencesExistMessageWhenConferenceListIsEmpty() throws Exception {
        when(conferenceRepository.fetchAllConferences()).thenReturn(new ArrayList<Conference>());
        ModelAndView actualModel = conferenceController.home();
        assertThat(actualModel.getViewName(), is(equalTo("conferencehome")));
        String noConferenceExistsMessage = (String)actualModel.getModel().get("emptyConferenceListMessage");
        assertThat(noConferenceExistsMessage, is(equalTo("No existing conferences !!")));
    }

    @Test
    public void shouldGoToTaskCreationFormFromConferenceView() throws Exception {
        when(conferenceRepository.fetchAllConferences()).thenReturn(new ArrayList<Conference>());
        UUID conferenceId = UUID.randomUUID();
        ModelAndView modelAndView =   conferenceController.showTaskCreationForm(""+conferenceId);
        assertThat(modelAndView.getViewName(), is("createtask"));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        TaskForm taskForm = new TaskForm();
        taskForm.setTask_name("Spring");
        taskForm.setTask_description("Yellow Fellow");
        UUID conferenceId = UUID.randomUUID();
        ModelAndView modelAndView =   conferenceController.createTask("" + conferenceId, taskForm);
        assertThat(modelAndView.getViewName(), is(equalTo("redirect:/zombie/admin/conference/"+conferenceId+"/create/question")));
    }

    @Test
    public void shouldStayOnCreateTaskFormIfAnyFieldLeftEmpty() throws Exception {
        UUID conferenceId = UUID.randomUUID();
        TaskForm taskForm = new TaskForm();
        taskForm.setTask_name("  ");
        taskForm.setTask_description("Yellow Fellow");
        ModelAndView modelAndView =   conferenceController.createTask("" + conferenceId, taskForm);
        Map<String, String> model = ((Map<String, String>) modelAndView.getModel().get("model"));
        assertThat(model.get("description"), is(equalTo("Yellow Fellow")));
        assertThat(modelAndView.getViewName(), is(equalTo("createtask")));

    }
}
