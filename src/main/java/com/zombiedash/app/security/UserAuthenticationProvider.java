package com.zombiedash.app.security;

import com.zombiedash.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

public class UserAuthenticationProvider implements AuthenticationProvider{
  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    try {
      userService.authenticateAndReturnUser((String) authentication.getPrincipal(),(String) authentication.getCredentials());
      return new UsernamePasswordAuthenticationToken(
          authentication.getPrincipal(),
          authentication.getCredentials(),
          new GrantedAuthority[] {new GrantedAuthority() {
            @Override
            public String getAuthority() {
              return "ROLE_ADMIN";
            }
          }});
    } catch (Exception e) {
      throw new BadCredentialsException("Bad User Credentials.");
    }

  }

  @Override
  public boolean supports(Class<?> aClass) {
    return true;
  }
}
