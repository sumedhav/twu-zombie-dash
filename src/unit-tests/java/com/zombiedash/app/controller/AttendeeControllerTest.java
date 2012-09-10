package com.zombiedash.app.controller;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class AttendeeControllerTest {
    @Test
    public void shouldDisplayCustomerHomePage() throws Exception {
        AttendeeController attendeeController =new AttendeeController();
        ModelAndView actualModel = attendeeController.showCustomerPage();
        assertThat(actualModel.getViewName(), is(equalTo("attendee")));
    }
}
