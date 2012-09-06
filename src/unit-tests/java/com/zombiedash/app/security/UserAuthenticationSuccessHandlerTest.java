package com.zombiedash.app.security;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserAuthenticationSuccessHandlerTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Authentication authentication;
    private UserAuthenticationSuccessHandler successHandler;

    @Before
    public void setUp() throws Exception {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        authentication = mock(Authentication.class);
        authentication.setAuthenticated(true);
        successHandler = new UserAuthenticationSuccessHandler();
    }

    @Test
    public void shouldRedirectToAdminHomeOnAuthenticationSuccessForAdmin() throws Exception {
        given(authentication.getPrincipal()).willReturn("admin");
        Collection authorities = asList(adminAuthority());
        given(authentication.getAuthorities()).willReturn(authorities);
        given(request.getContextPath()).willReturn("app");

        successHandler.onAuthenticationSuccess(request, response, authentication);

        verify(response).sendRedirect("app/zombie/admin/home");
    }

    @Test
    public void shouldRedirectToAttendeeHomeOnAuthenticationSuccessForAttendee() throws Exception {
        given(authentication.getPrincipal()).willReturn("attendee");
        given(request.getContextPath()).willReturn("app");
        AuthenticationSuccessHandler successHandler = new UserAuthenticationSuccessHandler();

        successHandler.onAuthenticationSuccess(request, response, authentication);

        verify(response).sendRedirect("app/zombie/attendee/home");

    }

    private GrantedAuthority adminAuthority() {
        return new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_ADMIN";
            }
        };
    }
}
