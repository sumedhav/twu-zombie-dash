package com.zombiedash.app.security;

import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import com.zombiedash.app.service.UserService;
import org.junit.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserAuthenticationProviderTest {

    @Test(expected = BadCredentialsException.class)
    public void shouldNotAuthenticateWithIncorrectCredentials() {
        UserService userService = mock(UserService.class);
        given(userService.authenticateAndReturnUser("admin", "bad password")).willThrow(BadCredentialsException.class);
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn("admin");
        when(authentication.getCredentials()).thenReturn("bad password");
        UserAuthenticationProvider userAuthenticationProvider = new UserAuthenticationProvider();
        userAuthenticationProvider.setUserService(userService);
        userAuthenticationProvider.authenticate(authentication);
    }

    @Test
    public void shouldAuthenticateUserWithCorrectCredentials() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn("admin");
        when(authentication.getCredentials()).thenReturn("correctPassword1");
        UserAuthenticationProvider userAuthenticationProvider = new UserAuthenticationProvider();
        UserService mockUserService = mock(UserService.class);
        userAuthenticationProvider.setUserService(mockUserService);
        when(mockUserService.authenticateAndReturnUser("admin", "correctPassword1")).thenReturn(
                new User("admin", Role.ADMIN, "name", "email"));
        Authentication authenticatedUser = userAuthenticationProvider.authenticate(authentication);
        assertThat((String) authenticatedUser.getPrincipal(), is(equalTo("admin")));
        assertThat((String) authenticatedUser.getCredentials(), is(equalTo("correctPassword1")));
    }

}
