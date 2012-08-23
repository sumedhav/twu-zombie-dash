package com.zombiedash.app.controller;

import com.zombiedash.app.model.User;
import com.zombiedash.app.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
        ModelAndView modelAndView = new LoginController(userService).showForm();

        assertThat(modelAndView.getViewName(), equalTo("loginform"));
    }

    @Test
    public void  shouldForwardToLoginSuccessIfUserSuccessfullyAuthenticated() throws Exception {
        User user = mock(User.class);
        given(userService.authenticateAndReturnUser(anyString(), anyString())).willReturn(user);
        String viewName = new LoginController(userService).processForm("admin","Welcome1").getViewName();

        assertThat(viewName,equalTo("loginsuccess"));
    }

    @Test
    public void shouldForwardToLoginFormIfUserUnsuccessfullyAuthenticated() throws Exception{
        given(userService.authenticateAndReturnUser(anyString(), anyString())).willThrow(Exception.class);
        ModelAndView modelAndView = new LoginController(userService).processForm("admin1","Welcome1");
        RedirectView redirectView = (RedirectView) modelAndView.getView();
        assertThat(redirectView.getUrl(), equalTo("/zombie/login/LoginForm"));
   }

    @Test
    public void shouldRedirectToLoginPageOnLogout() throws Exception{
        ModelAndView modelAndView = new LoginController(userService).redirectToLoginFormOnClickingLogout();
        RedirectView redirectView = (RedirectView) modelAndView.getView();
        assertThat(redirectView.getUrl(), equalTo("/zombie/login/LoginForm"));
    }
}
