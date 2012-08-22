package com.zombiedash.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ListUsersControllerTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void shouldReturnAViewWithNameListUsers() {
        ListUsersController listUsersController = new ListUsersController();
        String result = listUsersController.listUsers().getViewName();
        assertThat(result, is("listusers"));
    }

    @Test
    public void shouldReturnAListContainingUsers() {
        ListUsersController listUsersController = new ListUsersController();
        ModelAndView modelAndView = listUsersController.listUsers();
        List<String> list = (ArrayList<String>) modelAndView.getModel().get("Users");
        assertThat(list.get(0), is("User 1"));
    }
}
