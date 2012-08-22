package com.zombiedash.app.controller;

import com.zombiedash.app.model.User;
import com.zombiedash.app.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;

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
    public void  shouldForwardToLoginSuccessIfUserSuccessfullyAuthenticated() {
        User user = mock(User.class);
        given(userService.authenticateAndReturnUser(anyString(), anyString())).willReturn(user);

        String viewName = new LoginController(userService).processForm("admin","Welcome1", new MockHttpServletRequest());

        assertThat(viewName,equalTo("loginsuccess"));
    }

    @Test
    public void shouldForwardToLoginFormIfUserUnsuccessfullyAuthenticated(){
        given(userService.authenticateAndReturnUser(anyString(), anyString())).willReturn(null);

        String viewName = new LoginController(userService).processForm("admin1","Welcome1", new MockHttpServletRequest());

        assertThat(viewName, equalTo("loginform"));
   }
}
