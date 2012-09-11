package com.zombiedash.app.controller;

import com.zombiedash.app.service.ResultService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

public class AttendeeControllerTest {

    @Test
    public void shouldDisplayCustomerHomePage() throws Exception {
        ResultService userService = mock(ResultService.class);
        AttendeeController attendeeController =new AttendeeController(userService);
        ModelAndView actualModel = attendeeController.display(new HashMap<String, String>());
        assertThat(actualModel.getViewName(), is(equalTo("attendee")));
    }


}
