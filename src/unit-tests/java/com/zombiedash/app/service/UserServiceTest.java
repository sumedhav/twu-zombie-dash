package com.zombiedash.app.service;


import com.zombiedash.app.model.User;
import com.zombiedash.app.repository.UserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private UserService userService;
    @Mock
    UserRepository userRepository;
    @Mock
    User user;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        userService = new UserService(userRepository);
    }

    @Test
    public void shouldAuthenticateAndReturnUserForCorrectCredentials() throws Exception {
        given(userRepository.getUser("admin", "Welcome1")).willReturn(user);

        assertThat(userService.authenticateAndReturnUser("admin", "Welcome1"), sameInstance(user));
    }

    @Test (expected = Exception.class)
    public void shouldNotAuthenticateAndReturnNullForIncorrectPassword() throws Exception {
        given(userRepository.getUser("admin", "Welcome2")).willThrow(new Exception());
        userService.authenticateAndReturnUser("admin", "Welcome2");
    }

    @Test (expected = Exception.class)
    public void shouldNotAuthenticateAndReturnNullForIncorrectUserName() throws Exception {
        given(userRepository.getUser("sdfjbdvj", "Welcome1")).willThrow(new Exception());
        userService.authenticateAndReturnUser("sdfjbdvj", "Welcome1");
    }

    @Test (expected = Exception.class)
    public void shouldNotAuthenticateAndReturnNullForIncorrectCredentials() throws Exception {
        given(userRepository.getUser("admigfsdgnf", "Welcomdfgrfjgre1")).willThrow(new Exception());
        userService.authenticateAndReturnUser("admigfsdgnf", "Welcomdfgrfjgre1");
    }

    @Test
    public void shouldListUsers() {
        List<User> list = new ArrayList<User>();
        User admin = mock(User.class);

        list.add(admin);
        list.add(user);
        given(userRepository.retrieveAllUsers()).willReturn(list);

        List<User> result = userService.getAllUsers();

        assertThat(result.get(0), sameInstance(admin));
        assertThat(result.get(1), sameInstance(user));
    }

    @Test
    public void shouldGetUser() {
        given(userRepository.getUser("user")).willReturn(user);

        User result = userService.getUser("user");

        assertThat(result, sameInstance(user));
    }

    @Test
    public void shouldCreateNewUser() {
        given(userRepository.insertUser(user, "password1")).willReturn(true);

        Boolean userCreated = userService.createUser(user, "password1");

        assertThat(userCreated, is(true));
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldNotCreateUserIfUserAlreadyExists() throws Exception {
        given(user.getUserName()).willReturn("username");
        given(userRepository.userNameExists("username")).willReturn(true);

        userService.createUser(user, "password1");
        thrown.expectMessage("userNameAlreadyExists");
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        userService.deleteUser("user");
        verify(userRepository).deleteUser("user");
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionIfTryingToDeleteAdminUser() throws Exception {
        userService.deleteUser("admin");
    }
}
