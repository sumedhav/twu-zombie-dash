package com.zombiedash.app.repository;

import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        jdbcTemplate.execute("DELETE zombie_users");
        userRepository = new UserRepository(jdbcTemplate);
    }

    @Test
    public void shouldCheckIfAdminUserIsRetrieved() throws Exception{
        jdbcTemplate.execute("INSERT INTO zombie_users VALUES('admin', 'Welcome1', 0, 'Administrator', 'admin@zombie.com')");
        User adminUser = userRepository.retrieveAdminUser();
        assertTrue(adminUser.authenticate("admin", "Welcome1"));
    }

    @Test
    public void shouldRetrieveUser() {
        jdbcTemplate.execute("INSERT INTO zombie_users VALUES('admin', 'Welcome1', 0, 'Administrator', 'admin@zombie.com')");
        User result = userRepository.retrieveUser("admin");
        assertThat(result, is(new User("admin", "Welcome1", Role.ADMIN, "Administrator", "admin@zombie.com")));
    }

    @Test
    public void shouldRetrieveAllUsers() {
        jdbcTemplate.execute("INSERT INTO zombie_users VALUES('admin', 'Welcome1', 0, 'Administrator', 'admin@zombie.com')");
        jdbcTemplate.execute("INSERT INTO zombie_users VALUES('beta', 'password1', 1, 'Game Designer 1', 'gm1@zombie.com')");
        List<User> result = userRepository.retrieveAllUsers();
        assertThat(result.get(0), is(new User("admin", "Welcome1", Role.ADMIN, "Administrator", "admin@zombie.com")));
        assertThat(result.get(1), is(new User("beta", "password1", Role.GAME_DESIGNER, "Game Designer 1", "gm1@zombie.com")));
    }

    @Test
    public void shouldCreateUser() {
        userRepository.createUser(new User("designer", "password1", Role.GAME_DESIGNER, "MR.Right", "right@rightmail.com"));
        User designer = userRepository.retrieveUser("designer");
        assertThat(designer, is(new User("designer", "password1", Role.GAME_DESIGNER, "MR.Right", "right@rightmail.com")));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldNotAllowNullUserName(){
        userRepository.createUser(new User("", "password1", Role.GAME_DESIGNER, "MR.Right", "right@rightmail.com"));
        User designer = userRepository.retrieveUser("");
        assertThat(designer, is(new User("", "password1", Role.GAME_DESIGNER, "MR.Right", "right@rightmail.com")));
    }

    @Test(expected = DataAccessException.class)
    public void shouldDeleteUserFromDatabase(){
        jdbcTemplate.execute("INSERT INTO zombie_users VALUES('beta', 'password1', 1, 'Game Designer 1', 'gm1@zombie.com')");
        userRepository.deleteUser("beta");
        jdbcTemplate.queryForObject("SELECT username FROM zombie_users WHERE username = 'beta'", String.class);
    }
}
