package com.zombiedash.app.controller;

import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import com.zombiedash.app.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

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
        String result = usersController.createUser().getViewName();
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
        UsersController usersController = new UsersController(userService);
        ModelAndView modelAndView = usersController.createUserSubmit("designer", "password", "GameDesigner", "MR.Right", "right@gmail.com");
        verify(userService, times(1)).createUser(new User("designer", "password", Role.GAME_DESIGNER, "MR.Right", "right@gmail.com"));

        assertThat(modelAndView.getViewName(), is("redirect:/zombie/admin/users/"));
    }

    @Test
    public void shouldDisplayErrorPageIfCredentialValidationFails() {
        doThrow(new RuntimeException()).when(userService).createUser((User) anyObject());
        UsersController usersController = new UsersController(userService);
        ModelAndView modelAndView = usersController.createUserSubmit("", "password", "GameDesigner", "MR.Right", "right@gmail.com");
        verify(userService, times(1)).createUser(new User("", "password", Role.GAME_DESIGNER, "MR.Right", "right@gmail.com"));

        assertThat(modelAndView.getViewName(), is("redirect:/zombie/admin/users/errorPage/"));
    }

}
