package com.zombiedash.app.controller;

import com.zombiedash.app.model.User;
import com.zombiedash.app.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @Mock
    UserService userService;

    @Test
    public void shouldForwardToLoginForm() {
        String viewName = new LoginController(userService).showForm();

        assertThat(viewName, equalTo("loginform"));
    }

    @Test
    public void  shouldForwardToLoginSuccessIfUserSuccessfullyAuthenticated() throws Exception {
        User user = mock(User.class);
        given(userService.authenticateAndReturnUser(anyString(), anyString())).willReturn(user);
        String viewName = new LoginController(userService).processForm("admin","Welcome1", new ModelAndView()).getViewName();

        assertThat(viewName,equalTo("loginsuccess"));
    }

    @Test
    public void shouldForwardToLoginFormIfUserUnsuccessfullyAuthenticated() throws Exception{
        given(userService.authenticateAndReturnUser(anyString(), anyString())).willThrow(Exception.class);
        String viewName = new LoginController(userService).processForm("admin1","Welcome1", new ModelAndView()).getViewName();

        assertThat(viewName, equalTo("loginform"));
   }
}
