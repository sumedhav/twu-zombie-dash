package com.zombiedash.app.controller;


import com.zombiedash.app.error.ValidationMessagesMap;
import com.zombiedash.app.forms.RegistrationForm;
import com.zombiedash.app.model.Attendee;
import com.zombiedash.app.repository.ConferenceRepository;
import com.zombiedash.app.service.RegistrationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {

    @Mock
    RegistrationService attendeeRepository  ;
    @Mock
    ConferenceRepository conferenceRepository;
    @Mock
    private ValidationMessagesMap validationMessagesMap;

    private RegistrationController registrationController;
    @Before
    public void setup(){
       registrationController = new RegistrationController(conferenceRepository, attendeeRepository, validationMessagesMap);
    }

    @Test
    public void shouldGoToRegistrationPageIfConferenceIdIsValid(){
        UUID confId = UUID.randomUUID();
        when(conferenceRepository.isConferencePresent(confId)).thenReturn(true);
        ModelAndView result = registrationController.openRegistrationPage(confId.toString());
        assertThat(result.getViewName(),is("attendeeregistration"));
    }

    @Test
    public void shouldGoToErrorPageIfConferenceIdIsInvalid(){
        UUID confId = UUID.randomUUID();
        when(conferenceRepository.isConferencePresent(confId)).thenReturn(false);
        ModelAndView result = registrationController.openRegistrationPage(confId.toString());
        assertThat(result.getViewName(),is("404"));
    }

    @Test
    public void shouldValidateRegistrationFormAndPopulateErrorMessagesToModelAndViewWithPrePopulatedValues() throws Exception {
        List<String> errorCodes = new ArrayList<String>(){{add("errorCode");}};
        Map<String, String> model = new HashMap<String, String>();

        RegistrationForm registrationForm =  aRegistrationFormWithErrors(errorCodes, model);

        ModelAndView modelAndView = registrationController.submitRegistrationPage("conferenceId", registrationForm);

        verify(registrationForm).validate();
        verify(validationMessagesMap).getMessageFor("errorCode");
        assertThat(modelAndView.getModel().get("errorCode").toString(), is(equalTo("Error Message")));
        assertThat(modelAndView.getViewName(), is("attendeeregistration"));
        assertThat((Map<String, String>) modelAndView.getModel().get("model"), is(model));
    }

    @Test
    public void shouldSaveAttendeeIfValidationPasses() throws Exception {
        Attendee attendee =  mock(Attendee.class);
        UUID conferenceId = UUID.randomUUID();
        RegistrationForm registrationForm = aValidRegistrationForm(attendee);

        ModelAndView modelAndView = registrationController.submitRegistrationPage(conferenceId.toString(), registrationForm);

        verify(attendeeRepository).registerAttendee(attendee, "password1", conferenceId);
        assertThat(modelAndView.getViewName(), is("registrationconfirmed"));
    }

    @Test
    public void shouldGotoGeneralErrorPageIfSaveAttendeeFails() throws Exception {
        Attendee attendee =  mock(Attendee.class);
        UUID conferenceId = UUID.randomUUID();

        RegistrationForm registrationForm = aValidRegistrationForm(attendee);

        doThrow(Exception.class).when(attendeeRepository).registerAttendee(attendee, "password1", conferenceId);

        ModelAndView modelAndView = registrationController.submitRegistrationPage(conferenceId.toString(), registrationForm);
        assertThat(modelAndView.getViewName(), is(equalTo("generalerrorpage")));
    }

    private RegistrationForm aRegistrationFormWithErrors(List<String> errorCodes, Map<String, String> model) {
        RegistrationForm registrationForm = mock(RegistrationForm.class);
        when(registrationForm.hasErrors()).thenReturn(true);
        when(registrationForm.getErrors()).thenReturn(errorCodes);
        when((registrationForm.populateFormValuesToModelMap())).thenReturn(model);
        when(validationMessagesMap.getMessageFor("errorCode")).thenReturn("Error Message");
        return registrationForm;
    }

    private RegistrationForm aValidRegistrationForm(Attendee attendee) {
        RegistrationForm registrationForm = mock(RegistrationForm.class);
        when(registrationForm.createAttendee()).thenReturn(attendee);
        when(registrationForm.getPassword()).thenReturn("password1");
        return registrationForm;
    }

}
