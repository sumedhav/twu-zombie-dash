package com.zombiedash.app.service;


import com.zombiedash.app.model.User;
import com.zombiedash.app.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @Test
    public void shouldAuthenticateAndReturnUserForCorrectCredentials() throws Exception {
        given(userRepository.retrieveAdminUser()).willReturn(new User("admin", "Welcome1"));
        UserService userService = new UserService(userRepository);
        assertNotNull(userService.authenticateAndReturnUser("admin", "Welcome1"));
    }

    @Test (expected = Exception.class)
    public void shouldNotAuthenticateAndReturnNullForIncorrectPassword() throws Exception {
        given(userRepository.retrieveAdminUser()).willReturn(new User("admin", "Welcome1"));
        UserService userService = new UserService(userRepository);
        userService.authenticateAndReturnUser("admin", "Welcome2");
    }

    @Test (expected = Exception.class)
    public void shouldNotAuthenticateAndReturnNullForIncorrectUserName() throws Exception {
        given(userRepository.retrieveAdminUser()).willReturn(new User("admin", "Welcome1"));
        UserService userService = new UserService(userRepository);
        userService.authenticateAndReturnUser("admefhvjin", "Welcome1");
    }

    @Test (expected = Exception.class)
    public void shouldNotAuthenticateAndReturnNullForIncorrectCredentials() throws Exception {
        given(userRepository.retrieveAdminUser()).willReturn(new User("admin", "Welcome1"));
        UserService userService = new UserService(userRepository);
        userService.authenticateAndReturnUser("admefhvjin", "Welcomdfgrfjgre1");
    }

    @Test
    public void shouldListUsers() {
        List list = new ArrayList<User>();
        list.add(new User("admin", "password"));
        list.add(new User("user", "password"));
        given(userRepository.retrieveAllUsers()).willReturn(list);

        UserService userService = new UserService(userRepository);
        List<User> result = userService.getAllUsers();

        assertThat(result.get(0), is(new User("admin", "password")));
        assertThat(result.get(1), is(new User("user", "password")));
    }

    @Test
    public void shouldGetUser() {
        given(userRepository.retrieveUser("user")).willReturn(new User("user", "password"));

        UserService userService = new UserService(userRepository);
        User result = userService.getUser("user");

        assertThat(result, is(new User("user", "password")));
    }

    @Test
    public void shouldCreateNewUser() {
        User user = new User("designer", "password");
        given(userRepository.createUser(user)).willReturn(true);

        UserService userService = new UserService(userRepository);
        Boolean userCreated = userService.createUser("designer", "password");

        assertThat(userCreated, is(true));
    }
}
