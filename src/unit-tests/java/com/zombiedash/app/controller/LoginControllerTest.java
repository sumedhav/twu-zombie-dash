package com.zombiedash.app.controller;

import com.zombiedash.app.controller.LoginController;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class LoginControllerTest {
    @Test
    public void shouldReturnLoginFormViewName() {
        String viewName = new LoginController().showForm();

        assertThat(viewName, equalTo("loginform"));
    }

    @Test
    public void  shouldReturnLoginSuccessViewNameForBothCorrectInput() {

        String viewName = new LoginController().processForm("admin","Welcome1",new MockHttpServletRequest());

        assertThat(viewName,equalTo("loginsuccess"));
    }

    @Test
    public void shouldReturnLoginFormViewNameForAnyIncorrectInput(){
        String viewNameOne = new LoginController().processForm("admin", "123", new MockHttpServletRequest());
        String viewNameTwo = new LoginController().processForm("Yahaaa", "Welcome1", new MockHttpServletRequest());
        String viewNameThree = new LoginController().processForm("Yahyaaa", "123", new MockHttpServletRequest());

        assertThat(viewNameOne,equalTo("loginform"));
        assertThat(viewNameTwo,equalTo("loginform"));
        assertThat(viewNameThree,equalTo("loginform"));

   }
}
