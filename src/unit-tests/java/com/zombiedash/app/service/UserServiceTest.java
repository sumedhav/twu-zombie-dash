package com.zombiedash.app.service;


import com.zombiedash.app.model.User;
import com.zombiedash.app.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

    @Test
    public void shouldNotAuthenticateAndReturnNullForIncorrectCredentials() throws Exception {
        given(userRepository.retrieveAdminUser()).willReturn(new User("admin", "Welcome1"));
        UserService userService = new UserService(userRepository);
        assertNull(userService.authenticateAndReturnUser("admin", "Welcome2"));
        assertNull(userService.authenticateAndReturnUser("admefhvjin", "Welcome1"));
        assertNull(userService.authenticateAndReturnUser("admefhvjin", "Welcomdfgrfjgre1"));
    }

}
