package com.zombiedash.app.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class LogoutControllerTest {

    @Test
    public void shouldForwardToLoginForm() {
        ModelAndView modelAndView = new LogoutController().logout();

        assertThat(modelAndView.getViewName(), equalTo("loginform"));
        assertThat((String) modelAndView.getModel().get("messageToBeDisplayed"), equalTo("Successful logout"));
    }
}
