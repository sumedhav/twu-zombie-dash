package com.zombiedash.app.service;


import com.zombiedash.app.model.User;
import com.zombiedash.app.repository.UserRepository;
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

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldAuthenticateAndReturnUserForCorrectCredentials() throws Exception {
        User admin = mock(User.class);
        given(userRepository.getUser("admin", "Welcome1")).willReturn(admin);

        UserService userService = new UserService(userRepository);

        assertThat(userService.authenticateAndReturnUser("admin", "Welcome1"), sameInstance(admin));
    }

    @Test (expected = Exception.class)
    public void shouldNotAuthenticateAndReturnNullForIncorrectPassword() throws Exception {
        given(userRepository.getUser("admin", "Welcome2")).willThrow(new Exception());
        UserService userService = new UserService(userRepository);
        userService.authenticateAndReturnUser("admin", "Welcome2");
    }

    @Test (expected = Exception.class)
    public void shouldNotAuthenticateAndReturnNullForIncorrectUserName() throws Exception {
        given(userRepository.getUser("sdfjbdvj", "Welcome1")).willThrow(new Exception());
        UserService userService = new UserService(userRepository);
        userService.authenticateAndReturnUser("sdfjbdvj", "Welcome1");
    }

    @Test (expected = Exception.class)
    public void shouldNotAuthenticateAndReturnNullForIncorrectCredentials() throws Exception {
        given(userRepository.getUser("admigfsdgnf", "Welcomdfgrfjgre1")).willThrow(new Exception());
        UserService userService = new UserService(userRepository);
        userService.authenticateAndReturnUser("admigfsdgnf", "Welcomdfgrfjgre1");
    }

    @Test
    public void shouldListUsers() {
        List list = new ArrayList<User>();
        User admin = mock(User.class);
        User user = mock(User.class);

        list.add(admin);
        list.add(user);
        given(userRepository.retrieveAllUsers()).willReturn(list);

        UserService userService = new UserService(userRepository);
        List<User> result = userService.getAllUsers();

        assertThat(result.get(0), sameInstance(admin));
        assertThat(result.get(1), sameInstance(user));
    }

    @Test
    public void shouldGetUser() {
        User user = mock(User.class);
        given(userRepository.getUser("user")).willReturn(user);

        UserService userService = new UserService(userRepository);
        User result = userService.getUser("user");

        assertThat(result, sameInstance(user));
    }

    @Test
    public void shouldCreateNewUser() {
        User user = mock(User.class);
        given(userRepository.createUser(user, "password1")).willReturn(true);

        UserService userService = new UserService(userRepository);
        Boolean userCreated = userService.createUser(user, "password1");

        assertThat(userCreated, is(true));
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldNotCreateUserIfUserAlreadyExists() throws Exception {
        User user = mock(User.class);
        given(user.getUserName()).willReturn("username");
        given(userRepository.userNameExists("username")).willReturn(true);

        UserService userService = new UserService(userRepository);
        userService.createUser(user, "password1");
        thrown.expectMessage("userNameAlreadyExists");
    }
}
