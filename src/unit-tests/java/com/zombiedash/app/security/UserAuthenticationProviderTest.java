package com.zombiedash.app.security;

import com.zombiedash.app.model.User;
import com.zombiedash.app.service.UserService;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserAuthenticationProviderTest {

  @Test(expected = BadCredentialsException.class)
  public void shouldNotAuthenticateWithIncorrectCredentials(){
    UserService userService = mock(UserService.class);
    try {
      when(userService.authenticateAndReturnUser("admin","bad password")).thenAnswer(new Answer<User>() {
        @Override
        public User answer(InvocationOnMock invocation) throws Throwable {
          throw new Exception();
        }
      });
    } catch (Exception e) {}
    Authentication authentication = mock(Authentication.class);
    when(authentication.getPrincipal()).thenReturn("admin");
    when(authentication.getCredentials()).thenReturn("bad password");
    UserAuthenticationProvider userAuthenticationProvider = new UserAuthenticationProvider();
    userAuthenticationProvider.setUserService(userService);
    userAuthenticationProvider.authenticate(authentication);
  }

  @Test
  public void shouldAuthendicateUserWithCorrectCredentials() {
    UserService userService = mock(UserService.class);
    try {
      when(userService.authenticateAndReturnUser("admin", "correct password")).thenAnswer(new Answer<User>() {
        @Override
        public User answer(InvocationOnMock invocation) throws Throwable {
          return mock(User.class);
        }
      });
    } catch (Exception e) {
    }
    Authentication authentication = mock(Authentication.class);
    when(authentication.getPrincipal()).thenReturn("admin");
    when(authentication.getCredentials()).thenReturn("correct password");
    UserAuthenticationProvider userAuthenticationProvider = new UserAuthenticationProvider();
    userAuthenticationProvider.setUserService(userService);
    Authentication authenticatedUser = userAuthenticationProvider.authenticate(authentication);
    assertThat((String) authenticatedUser.getPrincipal(), is(equalTo("admin")));
    assertThat((String) authenticatedUser.getCredentials(),is(equalTo("correct password")));
  }
}
