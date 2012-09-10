package com.zombiedash.app.security;

import com.zombiedash.app.model.User;
import com.zombiedash.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.zombiedash.app.model.Role.ADMIN;

// Responsible for controlling the flow after a successful authentication
@Service
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private UserRepository userRepository;

    @Autowired
    public UserAuthenticationSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = userRepository.fetchUser(authentication.getPrincipal().toString());
        response.sendRedirect(request.getContextPath() + redirectUrl(user));
    }

    private String redirectUrl(User user) {
        if (user.getRole().equals(ADMIN)) {
            return "/zombie/admin/home";
        }
        return "/zombie/attendee/" + user.getUserId() + "/home";
    }

}
