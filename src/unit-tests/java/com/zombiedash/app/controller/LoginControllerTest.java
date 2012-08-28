package com.zombiedash.app.controller;

import com.zombiedash.app.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @Mock
    UserService userService;

    @Test
    public void shouldForwardToLoginForm() {
        ModelAndView modelAndView = new LoginController(userService).showForm();
        assertThat(modelAndView.getViewName(), equalTo("loginform"));
    }

    @Test
    public void shouldRedirectToLoginPageOnLogout() throws Exception{
        ModelAndView modelAndView = new LoginController(userService).redirectToLoginFormOnClickingLogout(new MockHttpServletRequest());
        assertThat(modelAndView.getViewName(), equalTo("redirect:/zombie/login/LoginForm"));
    }

    @Test
    public void shouldRedirectToHomePageIfSessionIsValid() throws Exception {
        ModelAndView modelAndView = new LoginController(userService).redirectToHomePageIfSessionPersists();
        assertThat(modelAndView.getViewName(), equalTo("loginsuccess"));
    }

}
