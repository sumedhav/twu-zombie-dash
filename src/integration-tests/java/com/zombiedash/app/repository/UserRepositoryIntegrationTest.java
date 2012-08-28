package com.zombiedash.app.repository;

import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.zombiedash.app.test.matchers.UserMatcher.isAUserWith;
import static org.apache.commons.codec.digest.DigestUtils.md5Hex;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
    public void shouldRetrieveUser() {
        jdbcTemplate.execute("INSERT INTO zombie_users VALUES('admin', 'Welcome1', 0, 'Administrator', 'admin@zombie.com')");
        User result = userRepository.getUser("admin");
        assertThat(result, isAUserWith("admin", Role.ADMIN, "Administrator", "admin@zombie.com"));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionIfNoUserIsFound() {
        User result = userRepository.getUser("whatever");
        assertThat(result, isAUserWith("admin", Role.ADMIN, "Administrator", "admin@zombie.com"));
    }

    @Test
    public void shouldRetrieveAllUsers() {
        jdbcTemplate.execute("INSERT INTO zombie_users VALUES('admin', 'Welcome1', 0, 'Administrator', 'admin@zombie.com')");
        jdbcTemplate.execute("INSERT INTO zombie_users VALUES('beta123', 'password1', 1, 'Game Designer 1', 'gm1@zombie.com')");
        List<User> result = userRepository.retrieveAllUsers();
        assertThat(result.get(0), isAUserWith("admin", Role.ADMIN, "Administrator", "admin@zombie.com"));
        assertThat(result.get(1), isAUserWith("beta123", Role.GAME_DESIGNER, "Game Designer 1", "gm1@zombie.com"));
    }

    @Test
    public void shouldCreateUser() {
        userRepository.createUser(new User("designer", Role.GAME_DESIGNER, "MR.Right", "right@rightmail.com"), "password1");
        User actualUser = retrieveUserByUsername("designer");
        assertThat(actualUser, isAUserWith("designer", Role.GAME_DESIGNER, "MR.Right", "right@rightmail.com"));
    }

    @Test
    public void shouldHashUserPassword() throws Exception {
        userRepository.createUser(new User("designer", Role.GAME_DESIGNER, "MR.Right", "right@rightmail.com"), "password1");
        String result = jdbcTemplate.queryForObject("SELECT password FROM zombie_users WHERE username = 'designer'", String.class);
        assertThat(result , is(md5Hex("password1")));
    }

    @Test
    public void shouldLookUpAuthenticationCredentialsAndReturnUsernameIfFound() throws Exception {
        jdbcTemplate.execute("INSERT INTO zombie_users VALUES('beta123', '" + md5Hex("password1") + "', 1, 'Game Designer 1', 'gm1@zombie.com')");
        User actualUser = userRepository.getUser("beta123", "password1");

        assertThat(actualUser, isAUserWith("beta123", Role.GAME_DESIGNER, "Game Designer 1", "gm1@zombie.com"));
    }

    @Test
    public void shouldDeleteUserFromDatabase(){
        jdbcTemplate.execute("INSERT INTO zombie_users VALUES('beta', 'password1', 1, 'Game Designer 1', 'gm1@zombie.com')");
        userRepository.deleteUser("beta");
        assertThat(jdbcTemplate.queryForInt("SELECT count(*) FROM zombie_users WHERE username = 'beta'"), is(0));
    }

    private User retrieveUserByUsername(String username) {
        List<User> users = jdbcTemplate.query("SELECT * FROM zombie_users WHERE username = '" + username + "'", new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                return new User(resultSet.getString("username"),
                        Role.generateRole(resultSet.getString("role")),
                        resultSet.getString("name"),
                        resultSet.getString("email"));
            }
        });
        assertThat(users.size(), is(1));
        return users.get(0);
    }
}
