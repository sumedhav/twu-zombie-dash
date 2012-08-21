package com.zombiedash.app.controller;

import com.zombiedash.app.controller.LoginController;
import com.zombiedash.app.model.User;
import com.zombiedash.app.service.UserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
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
        given(userService.authenticateAndReturnUser("test.username", "test.password")).willReturn(user);

        String viewName = new LoginController(userService).processForm("test.username","test.password");

        assertThat(viewName,equalTo("loginsuccess"));
    }

    @Test
    public void shouldForwardToLoginFormIfUserUnsuccessfullyAuthenticated(){
        given(userService.authenticateAndReturnUser("test.username", "test.password")).willThrow(new RuntimeException("WRROOOOONG"));

        String viewName = new LoginController(userService).processForm("test.username","test.password");

        assertThat(viewName, equalTo("loginform"));
   }
}
