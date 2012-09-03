package com.zombiedash.app.controller;

import com.zombiedash.app.error.ValidationMessagesMap;
import com.zombiedash.app.forms.UserForm;
import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import com.zombiedash.app.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
  private UserService userService;
  private ValidationMessagesMap validationMessagesMap;
  private UsersController usersController;

  @Before
  public void setUp() throws Exception {
    userService = mock(UserService.class);
    validationMessagesMap = mock(ValidationMessagesMap.class);
    usersController = new UsersController(userService, validationMessagesMap);
  }

  @Test
  public void shouldReturnAViewWithNameListUsers() {
    String result = usersController.listUsers().getViewName();
    assertThat(result, is("listusers"));
  }

  @Test
  public void shouldReturnAListContainingUsers() {
    List<User> list = new ArrayList<User>();
    User dummyUser = mock(User.class);
    when(dummyUser.getName()).thenReturn("alice").thenReturn("bob");
    list.add(dummyUser);
    list.add(dummyUser);
    given(userService.getAllUsers()).willReturn(list);

    ModelAndView modelAndView = usersController.listUsers();
    List<User> result = (List<User>) modelAndView.getModel().get("Users");

    assertThat(result.get(0).getName(), is("alice"));
    assertThat(result.get(1).getName(), is("bob"));
  }

  @Test
  public void shouldReturnAViewWithNameCreateUser() {
    String result = usersController.createUser().getViewName();
    assertThat(result, is("createuser"));
  }

  @Test
  public void shouldCreateAnUser() {
    UserForm userForm = mock(UserForm.class);
    when(userForm.hasErrors()).thenReturn(false);
    ModelAndView modelAndView = usersController.createUser(userForm);
    assertThat(modelAndView.getViewName(), is("redirect:/zombie/admin/users/list"));
  }

  @Test
  public void shouldDisplayErrorPageForUnExpectedException() {
    doThrow(new RuntimeException()).when(userService).createUser(argThat(isAUserWith("username", Role.GAME_DESIGNER, "MR Right", "right@gmail.com")), eq("password1"));
    ModelAndView modelAndView = usersController.createUser(new UserForm("username", "GameDesigner", "MR Right", "right@gmail.com", "password1"));

    assertThat(modelAndView.getViewName(), is("errorpage"));
  }


  @Test
  public void shouldStayOnTheCreateUserPageAndShowErrorMessageIfUserIsInvalid(){
    when(validationMessagesMap.getMessageFor("invalidUserName")).thenReturn("invalid user name");
    ModelAndView modelAndView = usersController.createUser(new UserForm(" ", "GameDesigner", "MR.Right", "right@gmail.com", "password1"));
    assertThat(modelAndView.getViewName(), is(equalTo("createuser")));
    assertThat(modelAndView.getModel().get("invalidUserName").toString(), is("invalid user name"));
    assertThat(modelAndView.getModel().get("model"), is(notNullValue()));
    assertThat(modelAndView.getModel().get("model").toString(),
        is(equalTo("{username= , password=password1, role=GameDesigner, name=MR.Right, email=right@gmail.com}")));

  }

  @Test
  public void shouldDisplayDetailsPageForSelectedUser() throws Exception {
    mockAdminUser();
    ModelAndView modelAndView = usersController.showUserDetails("admin");
    assertThat(modelAndView.getViewName(), is("userdetails"));
  }

  @Test
  public void shouldRetrieveUserDetails() throws Exception {
    mockAdminUser();
    ModelAndView result = usersController.showUserDetails("admin");
    assertThat(result.getModel().get("User").toString(), is(equalTo("{email=John@me.com, name=John, role=Administrator, userName=JohnnyBoy}")));
  }

  private void mockAdminUser() {
    User user = mock(User.class);
    when(user.getName()).thenReturn("John");
    when(user.getRole()).thenReturn(Role.ADMIN);
    when(user.getUserName()).thenReturn("JohnnyBoy");
    when(user.getEmail()).thenReturn("John@me.com");
    when(userService.getUser("admin")).thenReturn(user);
  }

  @Test
  public void shouldRedirectToDeleteLogicWhenDeleteButtonIsPressed() throws Exception {
    usersController.processDeleteUser("test.username");
    verify(userService).deleteUser("test.username");
  }


  @Test
  public void shouldGoBackToCreateUserPageIfPasswordHasNoNumbers() throws Exception {
    ModelAndView modelAndView = usersController.createUser(new UserForm("username", "GameDesigner", "Name", "email@email.com", "password"));
    assertThat(modelAndView.getViewName(), is("createuser"));    }

  @Test
  public void shouldGoBackToCreateUserPageIfPasswordHasNoAlphabets() throws Exception {
    ModelAndView modelAndView = usersController.createUser(new UserForm("username", "GameDesigner", "Name", "email@email.com", "13248343"));
    assertThat(modelAndView.getViewName(), is("createuser"));    }

  @Test
  public void shouldGoBackToCreateUserPageIfPasswordHasLessThanSixCharacters() throws Exception {
    ModelAndView modelAndView = usersController.createUser(new UserForm("username", "GameDesigner", "Name", "email@email.com", "p23w"));
    assertThat(modelAndView.getViewName(), is("createuser"));    }
}
