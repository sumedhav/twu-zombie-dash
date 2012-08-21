package com.zombiedash.app.unit.controller;

import com.zombiedash.app.controller.LoginController;
import org.junit.Test;

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
        String viewName = new LoginController().processForm("Yahya","12");

        assertThat(viewName,equalTo("loginsuccess"));
    }

    @Test
    public void shouldReturnLoginFormViewNameForAnyIncorrectInput(){
        String viewNameOne = new LoginController().processForm("Yahya", "123");
        String viewNameTwo = new LoginController().processForm("Yahaaa", "12");
        String viewNameThree = new LoginController().processForm("Yahyaaa", "123");

        assertThat(viewNameOne,equalTo("loginform"));
        assertThat(viewNameTwo,equalTo("loginform"));
        assertThat(viewNameThree,equalTo("loginform"));

   }
}
