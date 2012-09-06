package com.zombiedash.app.security;

import com.zombiedash.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import static java.util.Arrays.asList;

public class UserAuthenticationProvider implements AuthenticationProvider {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();
        String credentials = (String) authentication.getCredentials();
        userService.authenticateAndReturnUser(principal, credentials);
        return new UsernamePasswordAuthenticationToken(principal, credentials, asList(adminAuthority()));
    }

    private GrantedAuthority adminAuthority() {
        return new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_ADMIN";
            }
        };
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
