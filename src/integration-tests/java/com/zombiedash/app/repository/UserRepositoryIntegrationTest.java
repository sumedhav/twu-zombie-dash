package com.zombiedash.app.repository;

import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.zombiedash.app.test.matchers.UserMatcher.isAUserWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-application-context.xml")
public class UserRepositoryIntegrationTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder = new ShaPasswordEncoder(512);

  @Before
  public void setUp() throws Exception {
    jdbcTemplate.execute("DELETE zombie_users");
    userRepository = new UserRepository(jdbcTemplate);
  }

  @Test
  public void shouldRetrieveAllUsers() {
    jdbcTemplate.execute("INSERT INTO zombie_users VALUES('admin', 'Welcome1', 0, 'Administrator', 'admin@zombie.com')");
    jdbcTemplate.execute("INSERT INTO zombie_users VALUES('beta123', 'password1', 1, 'First Game Designer', 'gm1@zombie.com')");
    jdbcTemplate.execute("INSERT INTO zombie_users VALUES('charlie', 'qwerty1234', 1, 'Second Game Designer', 'gm2@zombie.com')");
    List<User> result = userRepository.retrieveAllUsers();
    assertThat(result.get(0), isAUserWith("admin", Role.ADMIN, "Administrator", "admin@zombie.com"));
    assertThat(result.get(1), isAUserWith("beta123", Role.GAME_DESIGNER, "First Game Designer", "gm1@zombie.com"));
    assertThat(result.get(2), isAUserWith("charlie", Role.GAME_DESIGNER, "Second Game Designer", "gm2@zombie.com"));

  }

  @Test
  public void shouldRetrieveGivenUserIfUserExists() {
    jdbcTemplate.execute("INSERT INTO zombie_users VALUES('admin', 'Welcome1', 0, 'Administrator', 'admin@zombie.com')");
    User result = userRepository.getUser("admin");
    assertThat(result, isAUserWith("admin", Role.ADMIN, "Administrator", "admin@zombie.com"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowRuntimeExceptionIfNoUserIsFound() {
    userRepository.getUser("");
  }

  @Test
  public void shouldLookUpAuthenticationCredentialsAndReturnUsernameIfFound() {
    jdbcTemplate.execute("INSERT INTO zombie_users VALUES('beta123', '" + passwordEncoder.encodePassword("password1", UserRepository.SALT) + "', 1, 'Game Designer 1', 'gm1@zombie.com')");
    User actualUser = userRepository.getUser("beta123", "password1");
    assertThat(actualUser, isAUserWith("beta123", Role.GAME_DESIGNER, "Game Designer 1", "gm1@zombie.com"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowInvalidArgumentExceptionIfCredentialsAreBad() {
    jdbcTemplate.execute("INSERT INTO zombie_users VALUES('username', '" + passwordEncoder.encodePassword("password", UserRepository.SALT) + "', 1, '', '')");
    userRepository.getUser("beta123", "password1");
  }

  @Test
  public void shouldCreateUser() {
    Boolean userCreationFlag = userRepository.createUser(new User("designer", Role.GAME_DESIGNER, "MR.Right", "right@rightmail.com"), "password1");
    User actualUser = retrieveUserByUsername("designer");
    assertThat(userCreationFlag,is(equalTo(true)));
    assertThat(actualUser, isAUserWith("designer", Role.GAME_DESIGNER, "MR.Right", "right@rightmail.com"));
  }

  @Test
  public void shouldHashUserPassword() throws Exception {
    userRepository.createUser(new User("designer", Role.GAME_DESIGNER, "MR.Right", "right@rightmail.com"), "password1");
    String result = jdbcTemplate.queryForObject("SELECT password FROM zombie_users WHERE username = 'designer'", String.class);
    assertThat(result , is(equalTo(passwordEncoder.encodePassword("password1", UserRepository.SALT))));
  }

  @Test
  public void shouldDeleteUserFromDatabase() {
    jdbcTemplate.execute("INSERT INTO zombie_users VALUES('beta', 'password1', 1, 'Game Designer 1', 'gm1@zombie.com')");
    userRepository.deleteUser("beta");
    assertThat(jdbcTemplate.queryForInt("SELECT count(*) FROM zombie_users WHERE username = 'beta'"), is(0));
  }

  @Test
  public void existentUsernameShouldReturnTrue() {
    jdbcTemplate.execute("INSERT INTO zombie_users VALUES('username', '', 0, '', '')");
    Boolean usernameExistsFlag = userRepository.userNameExists("username");
    assertThat(usernameExistsFlag, is(equalTo(true)));
  }

  @Test
  public void nonexistentUsernameShouldReturnFalse() {
    jdbcTemplate.execute("INSERT INTO zombie_users VALUES('username', '', 0, '', '')");
    Boolean usernameExistsFlag = userRepository.userNameExists("");
    assertThat(usernameExistsFlag, is(equalTo(false)));
  }

  private User retrieveUserByUsername(String username) {
    List<User> users = jdbcTemplate.query("SELECT * FROM zombie_users WHERE username = '" + username + "'", new RowMapper<User>() {
      @Override
      public User mapRow(ResultSet resultSet, int i) throws SQLException {
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
