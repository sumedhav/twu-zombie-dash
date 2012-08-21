package com.zombiedash.app.controller;

import com.zombiedash.app.controller.LoginController;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class LoginControllerTest {
    @Test
    public void shouldReturnLoginFormViewName() {
        String viewName = new LoginController().showForm();

        assertThat(viewName, equalTo("loginform"));
    }

    @Test
    public void  shouldReturnLoginSuccessViewNameForBothCorrectInput() {

        String viewName = new LoginController().processForm("Yahya","12",new MockHttpServletRequest());

        assertThat(viewName,equalTo("loginsuccess"));
    }

    @Test
    public void shouldReturnLoginFormViewNameForAnyIncorrectInput(){
        String viewNameOne = new LoginController().processForm("Yahya", "123", new MockHttpServletRequest());
        String viewNameTwo = new LoginController().processForm("Yahaaa", "12", new MockHttpServletRequest());
        String viewNameThree = new LoginController().processForm("Yahyaaa", "123", new MockHttpServletRequest());

        assertThat(viewNameOne,equalTo("loginform"));
        assertThat(viewNameTwo,equalTo("loginform"));
        assertThat(viewNameThree,equalTo("loginform"));

   }
}
