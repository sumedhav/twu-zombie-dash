package com.zombiedash.app.controller;

import com.zombiedash.app.repository.ConferenceRepository;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

public class ConferenceControllerTest {
  @Test
  public void viewShouldBeCreateConference() throws Exception {
    ConferenceRepository conferenceRepository = mock(ConferenceRepository.class);
    ConferenceController conferenceController = new ConferenceController(conferenceRepository);
    ModelAndView actualModel = conferenceController.createConference();
    assertThat(actualModel.getViewName(),is(equalTo("createconference")));
  }

  @Test
  public void clickOnConferenceHomeShouldGoToConferenceHomePage() throws Exception {
    ConferenceRepository conferenceRepository = mock(ConferenceRepository.class);
    ConferenceController conferenceController = new ConferenceController(conferenceRepository);
    ModelAndView actualModel = conferenceController.home();
    assertThat(actualModel.getViewName(), is(equalTo("conferencehome")));
  }

  @Test
  public void submitShouldGoToHome() throws Exception {
    ConferenceRepository conferenceRepository = mock(ConferenceRepository.class);
    ConferenceController conferenceController = new ConferenceController(conferenceRepository);
    ModelAndView actualModel = conferenceController.createConference("NotNull", "NotNull", "NotNull", "NotNull", "2013-03-04", "2013-03-04", "10");
    assertThat(actualModel.getViewName(),is(equalTo("redirect:/zombie/admin/conference/list")));
  }

  @Test
  public void submitWithIncompleteFormShouldRemainOnCreateForm() throws Exception {
    ConferenceRepository conferenceRepository = mock(ConferenceRepository.class);
    ConferenceController conferenceController = new ConferenceController(conferenceRepository);
    ModelAndView actualModel = conferenceController.createConference("NotNull", "", "NotNull", "NotNull", "NotNull", "NotNull", "1");
    assertThat(actualModel.getViewName(), is(equalTo("redirect:/zombie/admin/conference/create")));
  }
}
