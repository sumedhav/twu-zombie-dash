package com.zombiedash.app.controller;

import java.util.ArrayList;
import java.util.List;

import com.zombiedash.app.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UsersControllerTest {
    @Mock
    UserService userService;

    @Test
    public void shouldReturnAViewWithNameListUsers() {
        UsersController usersController = new UsersController(userService);
        String result = usersController.listUsers().getViewName();
        assertThat(result, is("listusers"));
    }

    @Test
    public void shouldReturnAViewWithNameCreateUser() {
        UsersController usersController = new UsersController(userService);
        String result = usersController.createUser("user", "password").getViewName();
        assertThat(result, is("createuser"));
    }

    @Test
    public void shouldReturnAListContainingUsers() {
        List<String> list = new ArrayList<String>();
        list.add("User 1");
        list.add("User 2");
        given(userService.getAllUsers()).willReturn(list);

        UsersController usersController = new UsersController(userService);
        ModelAndView modelAndView = usersController.listUsers();
        List<String> result = (ArrayList<String>) modelAndView.getModel().get("Users");

        assertThat(result.get(0), is("User 1"));
        assertThat(result.get(1), is("User 2"));
    }

    @Test
    public void shouldCreateAnUser() {
        given(userService.createUser("designer", "password")).willReturn(true);

        UsersController usersController = new UsersController(userService);
        usersController.createUser("designer", "password");
        verify(userService, times(1)).createUser("designer", "password");


//        ModelAndView modelAndView = usersController.listUsers();
//        List<String> result = (ArrayList<String>) modelAndView.getModel().get("Users");
//
//        assertThat(result.get(0), is("Username: admin, Password: Welcome1"));
//        assertThat(result.get(1), is("Username: designer, Password: password"));

    }
}
