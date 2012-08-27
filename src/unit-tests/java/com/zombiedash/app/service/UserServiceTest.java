package com.zombiedash.app.service;


import com.zombiedash.app.model.Role;
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
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

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
        list.add(new User("admin", "password1"));
        list.add(new User("user", "password1"));
        given(userRepository.retrieveAllUsers()).willReturn(list);

        UserService userService = new UserService(userRepository);
        List<User> result = userService.getAllUsers();

        assertThat(result.get(0), is(new User("admin", "password1")));
        assertThat(result.get(1), is(new User("user", "password1")));
    }

    @Test
    public void shouldGetUser() {
        given(userRepository.retrieveUser("user")).willReturn(new User("user", "password1"));

        UserService userService = new UserService(userRepository);
        User result = userService.getUser("user");

        assertThat(result, is(new User("user", "password1")));
    }

    @Test
    public void shouldCreateNewUser() {
        User user = new User("designer", "password1", Role.GAME_DESIGNER, "MR.Right", "right@gmail.com");
        given(userRepository.createUser(user)).willReturn(true);

        UserService userService = new UserService(userRepository);
        Boolean userCreated = userService.createUser(new User("designer", "password1", Role.GAME_DESIGNER, "MR.Right", "right@gmail.com"));

        assertThat(userCreated, is(true));
    }

}
