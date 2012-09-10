package com.zombiedash.app.security;

import com.zombiedash.app.model.User;
import com.zombiedash.app.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import static com.zombiedash.app.model.Role.ADMIN;
import static com.zombiedash.app.model.Role.ATTENDEE;
import static java.util.Arrays.asList;
import static java.util.UUID.randomUUID;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class UserAuthenticationSuccessHandlerTest {

    private UserRepository userRepository = mock(UserRepository.class);
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);
    private Authentication authentication = mock(Authentication.class);
    private UserAuthenticationSuccessHandler successHandler;

    @Before
    public void setUp() throws Exception {
        authentication.setAuthenticated(true);
        successHandler = new UserAuthenticationSuccessHandler(userRepository);
    }

    @Test
    public void shouldRedirectToAdminHomeOnAuthenticationSuccessForAdmin() throws Exception {
        UUID userId = randomUUID();
        String username = "admin";
        User user = new User(userId, username, ADMIN, "silvio the admin", "italiano@mailinator.com");
        when(userRepository.fetchUser(username)).thenReturn(user);

        given(authentication.getPrincipal()).willReturn(username);
        Collection authorities = asList(adminAuthority());
        given(authentication.getAuthorities()).willReturn(authorities);
        given(request.getContextPath()).willReturn("app");
        successHandler.onAuthenticationSuccess(request, response, authentication);
        verify(response).sendRedirect("app/zombie/admin/home");
    }

    @Test
    public void shouldRedirectToAttendeeHomeOnAuthenticationSuccessForUserCalledRaphael() throws Exception {
        UUID userId = randomUUID();
        String username = "raphael";
        User user = new User(userId, username, ATTENDEE, "raphael oliveira", "brasileiro@mailinator.com");
        when(userRepository.fetchUser(username)).thenReturn(user);

        given(authentication.getPrincipal()).willReturn(username);
        given(request.getContextPath()).willReturn("app");
        successHandler.onAuthenticationSuccess(request, response, authentication);
        verify(response).sendRedirect("app/zombie/attendee/" + userId + "/home");
    }

    @Test
    public void shouldRedirectToAttendeeHomeOnAuthenticationSuccessForUserCalledCharles() throws IOException, ServletException {
        UUID userId = randomUUID();
        String username = "charles";
        User user = new User(userId, username, ATTENDEE, "charles kimpolo", "cramo@mailinator.com");
        when(userRepository.fetchUser(username)).thenReturn(user);

        given(authentication.getPrincipal()).willReturn(username);
        given(request.getContextPath()).willReturn("app");
        successHandler.onAuthenticationSuccess(request, response, authentication);
        verify(response).sendRedirect("app/zombie/attendee/" + userId + "/home");
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
