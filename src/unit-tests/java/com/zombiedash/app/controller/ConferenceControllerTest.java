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
    ModelAndView actualModel = conferenceController.create();
    assertThat(actualModel.getViewName(),is(equalTo("createconference")));
  }

  @Test
  public void submitShouldGoToHome() throws Exception {
    ConferenceRepository conferenceRepository = mock(ConferenceRepository.class);
    ConferenceController conferenceController = new ConferenceController(conferenceRepository);
    ModelAndView actualModel = conferenceController.submit("","","","","","","0","","","");
    assertThat(actualModel.getViewName(),is(equalTo("home")));
  }
}
