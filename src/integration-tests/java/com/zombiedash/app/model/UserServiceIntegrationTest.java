package com.zombiedash.app.model;

import com.zombiedash.app.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-application-context.xml")

public class UserServiceIntegrationTest {
    @Autowired
    UserService userService;

    @Test
    public void shouldRetrieveAdminFromDatabase() throws Exception {
        User user = userService.createAdmin();
        user.authenticate("admin", "Welcome1".toCharArray());
    }
}
