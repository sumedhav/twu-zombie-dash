package com.zombiedash.app.controller;

import com.zombiedash.app.model.Attendee;
import com.zombiedash.app.model.Conference;
import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import com.zombiedash.app.repository.AttendeeRepository;
import com.zombiedash.app.repository.ConferenceRepository;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttendeeControllerTest {

    @Test
    public void shouldDisplayAttendeeHomepage() throws Exception {
        ConferenceRepository conferenceRepository = mock(ConferenceRepository.class);
        AttendeeRepository attendeeRepository = mock(AttendeeRepository.class);
        AttendeeController attendeeController = new AttendeeController(conferenceRepository, attendeeRepository);

        UUID conferenceId = randomUUID();
        when(conferenceRepository.findConferenceById(conferenceId)).thenReturn(conference());
        UUID attendeeId = randomUUID();
        User user = new User("Tejinder", Role.ATTENDEE, "Klamour", "clovea@twu.com");
        Attendee attendee = new Attendee(user, attendeeId, "2012-06-12", "India", "0800282820", "2050", "0820854959");
        when(attendeeRepository.findAttendeeById(attendeeId)).thenReturn(attendee);

        ModelAndView actualModel = attendeeController.display(attendeeId);
        ModelMap modelMap = actualModel.getModelMap();
        Attendee attendeeFromMap = (Attendee) modelMap.get("attendee");

        assertThat(actualModel.getViewName(), is(equalTo("attendee")));
        assertThat(modelMap, hasKey("attendee"));
        assertThat(modelMap, hasKey("conference"));
        assertThat(attendeeFromMap.getUsername(), is("Tejinder"));
    }


    private Conference conference() {
        return new Conference(10l, "fake conference", "topic", "what is", "venue", "2002-10-10", "2012-12-12", 123);
    }
}
