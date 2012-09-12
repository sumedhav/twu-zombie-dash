package com.zombiedash.app.security;

import com.zombiedash.app.helper.UserTestDataManager;
import com.zombiedash.app.repository.UserRepository;
import com.zombiedash.app.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-application-context.xml")
public class UserAuthenticationIntegrationTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new ShaPasswordEncoder(512);
    private static final Object SALT = null;
    private UserTestDataManager userTestDataManager;

    @Before
    public void setUp() {
        userTestDataManager = new UserTestDataManager(jdbcTemplate);
    }

    @Test
    @Rollback(true)
    public void shouldAuthenticateAttendee(){
        UserService userService=new UserService(userRepository);
        setUpAttendeeData();
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn("attendee");
        when(authentication.getCredentials()).thenReturn("password1");
        UserAuthenticationProvider userAuthenticationProvider=new UserAuthenticationProvider();
        userAuthenticationProvider.setUserService(userService);
        Authentication authentication1=userAuthenticationProvider.authenticate(authentication);
        assertEquals(authentication1.getName(),"attendee");
    }

    private void setUpAttendeeData(){
        userTestDataManager.insertAttendeeWithGenericConference(UUID.randomUUID().toString(),"attendee","password1","attendee name","email@email.com","1990-01-01","INDIA",null,null,null);
    }

}
