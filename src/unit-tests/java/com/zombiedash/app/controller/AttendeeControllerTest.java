package com.zombiedash.app.controller;

import com.zombiedash.app.repository.QuestionRepository;
import com.zombiedash.app.service.ResultService;
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
        QuestionRepository questionRepository = mock(QuestionRepository.class);
        ResultService userService = mock(ResultService.class);
        AttendeeController attendeeController =new AttendeeController(questionRepository,userService);
        ModelAndView actualModel = attendeeController.showCustomerPage(new HashMap<String, String>());
        assertThat(actualModel.getViewName(), is(equalTo("attendee")));
    }


}
