package com.zombiedash.app.controller;

import com.zombiedash.app.repository.AttendeeScoreRepository;
import com.zombiedash.app.service.ResultService;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

public class AttendeeControllerTest {

    @Test
    public void shouldDisplayCustomerHomePage() throws Exception {
        ResultService userService = mock(ResultService.class);
        Principal principal=mock(Principal.class);
        AttendeeScoreRepository resultRepository=mock(AttendeeScoreRepository.class);
        AttendeeController attendeeController =new AttendeeController(userService,resultRepository);
        ModelAndView actualModel = attendeeController.display(principal);
        assertThat(actualModel.getViewName(), is(equalTo("attendee")));
    }


}
