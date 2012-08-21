package com.zombiedash.app.model;


import com.zombiedash.app.service.UserService;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Test
    public void shouldRetrieveAdminFromDatabase() throws Exception {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(Class.class))).thenReturn("admin").thenReturn("Welcome1");
        UserService userService = new UserService(jdbcTemplate);
        User user = userService.createAdmin();
        user.authenticate("admin", "Welcome1".toCharArray());
    }
}
