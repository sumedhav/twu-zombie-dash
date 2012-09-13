package com.zombiedash.app.repository;

import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserRepositoryTest {
    JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
    UserRepository userRepository;

    @Before
    public void setup(){
        userRepository = new UserRepository(jdbcTemplate);
    }

    @Test
    public void shouldCheckIfUserExists() throws Exception {
        List listOfUsers = new ArrayList(){{add(new User("username", Role.ADMIN, "vcfdc", "email@email.com"));}};
        when(jdbcTemplate.query(anyString(),any(Object[].class), any(RowMapper.class))).thenReturn(listOfUsers);
        boolean result = userRepository.userNameExists("username");
        assertThat(result, is(true));
    }

    @Test
    public void shouldCheckIfUserDoesNotExist() throws Exception {
        List listOfUsers = new ArrayList();
        when(jdbcTemplate.query(anyString(),any(Object[].class), any(RowMapper.class))).thenReturn(listOfUsers);
        boolean result = userRepository.userNameExists("username");
        assertThat(result, is(false));
    }
}
