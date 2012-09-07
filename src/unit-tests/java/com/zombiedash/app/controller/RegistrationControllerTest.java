package com.zombiedash.app.controller;


import com.zombiedash.app.repository.ConferenceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {
    @Mock
    ConferenceRepository conferenceRepository;

    @Test
    public void shouldGoToRegistrationPageIfConferenceIdIsValid(){
        UUID confId = UUID.randomUUID();
        when(conferenceRepository.isConferencePresent(confId)).thenReturn(true);
        RegistrationController registrationController = new RegistrationController(conferenceRepository);
        ModelAndView result = registrationController.openRegistrationPage(confId.toString());
        assertThat(result.getViewName(),is("attendeeregistration"));
    }

    @Test
    public void shouldGoToErrorPageIfConferenceIdIsInvalid(){
        UUID confId = UUID.randomUUID();
        when(conferenceRepository.isConferencePresent(confId)).thenReturn(false);
        RegistrationController registrationController = new RegistrationController(conferenceRepository);
        ModelAndView result = registrationController.openRegistrationPage(confId.toString());
        assertThat(result.getViewName(),is("404"));
    }

}
