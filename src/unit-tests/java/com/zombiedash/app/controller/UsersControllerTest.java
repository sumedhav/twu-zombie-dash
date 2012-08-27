package com.zombiedash.app.controller;

import com.zombiedash.app.error.ValidationMessagesMap;
import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import com.zombiedash.app.service.UserService;
import org.hamcrest.core.IsSame;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static com.zombiedash.app.test.matchers.UserMatcher.isAUserWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UsersControllerTest {
    @Mock
    UserService userService;
    @Mock   @SuppressWarnings("unused")
    private ValidationMessagesMap validationMessagesMap;
    private UsersController usersController;

    @Before
    public void setUp() throws Exception {
        usersController = new UsersController(userService, validationMessagesMap);
    }

    @Test
    public void shouldReturnAViewWithNameListUsers() {
        String result = usersController.listUsers().getViewName();
        assertThat(result, is("listusers"));
    }

    @Test
    public void shouldReturnAViewWithNameCreateUser() {
        String result = usersController.createUser().getViewName();
        assertThat(result, is("createuser"));
    }

    @Test
    public void shouldReturnAListContainingUsers() {
        List<String> list = new ArrayList<String>();
        list.add("User 1");
        list.add("User 2");
        given(userService.getAllUsers()).willReturn(list);

        ModelAndView modelAndView = usersController.listUsers();
        List<String> result = (ArrayList<String>) modelAndView.getModel().get("Users");

        assertThat(result.get(0), is("User 1"));
        assertThat(result.get(1), is("User 2"));
    }

    @Test
    public void shouldCreateAnUser() {
        ModelAndView modelAndView = usersController.createUser("designer", "password1", "GameDesigner", "MR.Right", "right@gmail.com");
        verify(userService).createUser(argThat(isAUserWith("designer", "password1", Role.GAME_DESIGNER, "MR.Right", "right@gmail.com")));

        assertThat(modelAndView.getViewName(), is("redirect:/zombie/admin/users"));
    }

    @Test
    public void shouldDisplayErrorPageIfCredentialValidationFails() {
        doThrow(new RuntimeException()).when(userService).createUser(argThat(isAUserWith("", "password1", Role.GAME_DESIGNER, "MR.Right", "right@gmail.com")));
        ModelAndView modelAndView = usersController.createUser("", "password1", "GameDesigner", "MR.Right", "right@gmail.com");

        assertThat(modelAndView.getViewName(), is("redirect:/zombie/admin/users/errorPage"));
    }

    @Test
    public void shouldStayOnTheCreateUserPageAndShowErrorMessageIfUserIsInvalid(){
        when(validationMessagesMap.getMessageFor("invalidUserName")).thenReturn("invalid user name");
        ModelAndView modelAndView = usersController.createUser(" ", "password1", "GameDesigner", "MR.Right", "right@gmail.com");
        assertThat(modelAndView.getViewName(), is(equalTo("createuser")));
        assertThat(modelAndView.getModel().get("validationMessage").toString(), is("invalid user name"));
        assertThat(modelAndView.getModel().get("model"), is(notNullValue()));
        assertThat(modelAndView.getModel().get("model").toString(),
        is(equalTo("{username= , password=password1, role=GameDesigner, name=MR.Right, email=right@gmail.com}")));

    }

    @Test
    public void shouldDisplayDetailsPageForSelectedUser() throws Exception {
        ModelAndView modelAndView = usersController.showUserDetails("admin");
        assertThat(modelAndView.getViewName(), is("userDetails"));
    }

    @Test
    public void shouldRetrieveUserDetails() throws Exception {
        User expectedUser = mock(User.class);
        when(userService.getUser("admin")).thenReturn(expectedUser);

        ModelAndView result = usersController.showUserDetails("admin");

        assertThat((User) result.getModel().get("User"), IsSame.sameInstance(expectedUser));
    }

    @Test
    public void shouldRedirectToDeleteLogicWhenDeleteButtonIsPressed() throws Exception {
        usersController.processDeleteUser("test.username");
        verify(userService).deleteUser("test.username");
    }
}
