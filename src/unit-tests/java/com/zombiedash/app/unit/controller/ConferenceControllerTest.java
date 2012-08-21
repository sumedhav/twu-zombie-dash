package com.zombiedash.app.unit.controller;

import com.zombiedash.app.controller.ConferenceController;
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
        ConferenceController conferenceController = new ConferenceController();
        ModelAndView actualModel = conferenceController.create();
        assertThat(actualModel.getViewName(),is(equalTo("createconference")));
    }

    @Test
    public void submitShouldGoToHome() throws Exception {
        ConferenceController conferenceController = new ConferenceController();
        ConferenceRepository conferenceService = mock(ConferenceRepository.class);
        ModelAndView actualModel = conferenceController.submit("","","","","","","0","","","",conferenceService);
        assertThat(actualModel.getViewName(),is(equalTo("home")));
    }
}
