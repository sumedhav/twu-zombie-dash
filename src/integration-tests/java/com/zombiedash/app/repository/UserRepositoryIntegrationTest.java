package com.zombiedash.app.repository;

import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-application-context.xml")
public class UserRepositoryIntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void shouldCheckIfAdminUserIsRetrieved() throws Exception{
        UserRepository userRepository = new UserRepository(jdbcTemplate);
        User adminUser = userRepository.retrieveAdminUser();
        assertTrue(adminUser.authenticate("admin", "Welcome1"));
    }

    @Test
    public void shouldRetrieveUser() {
        UserRepository userRepository = new UserRepository(jdbcTemplate);
        User result = userRepository.retrieveUser("admin");
        assertThat(result, is(new User("admin", "Welcome1")));
    }

    @Test
    public void shouldRetrieveAllUsers() {
        UserRepository userRepository = new UserRepository(jdbcTemplate);
        List<User> result = userRepository.retrieveAllUsers();
        assertThat(result.get(0), is(new User("admin", "Welcome1")));
    }

    @Test
    public void shouldCreateUser() {
        UserRepository userRepository = new UserRepository(jdbcTemplate);
        userRepository.createUser(new User("designer", "password1", Role.GAME_DESIGNER, "MR.Right", "right@rightmail.com"));
        User designer = userRepository.retrieveUser("designer");
        assertThat(designer, is(new User("designer", "password1", Role.GAME_DESIGNER, "Mr.Right", "right@rightmail.com")));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldNotAllowNullUserName(){
        UserRepository userRepository = new UserRepository(jdbcTemplate);
        userRepository.createUser(new User("", "password1", Role.GAME_DESIGNER, "MR.Right", "right@rightmail.com"));
        User designer = userRepository.retrieveUser("");
        assertThat(designer, is(new User("", "password1", Role.GAME_DESIGNER, "Mr.Right", "right@rightmail.com")));
    }
}
