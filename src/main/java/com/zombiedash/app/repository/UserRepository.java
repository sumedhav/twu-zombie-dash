package com.zombiedash.app.repository;

import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {
    private JdbcTemplate jdbcTemplate;
    private PasswordEncoder passwordEncoder = new ShaPasswordEncoder(512);
    private static final String RETRIEVE_USER_BY_USERNAME = "SELECT * FROM zombie_users where username = ?";
    private static final String RETRIEVE_USER_BY_USERNAME_AND_PASSWORD = "SELECT * FROM zombie_users where username = ? and password = ?";
    private static final String RETRIEVE_ALL_USERS = "SELECT * FROM zombie_users";
    private static final String INSERT_USER = "INSERT INTO zombie_users values (?,?,?,?,?)";
    public static final Object SALT = null;

  @Autowired
  public UserRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<User> fetchAllUsers() {
    return jdbcTemplate.query(RETRIEVE_ALL_USERS, userMapper());
  }

  public User fetchUser(String username){
    List<User> userList = fetchAllUsersWithUserName(username);
    if(userList.size() == 0) throw new IllegalArgumentException("Username not found");
    return userList.get(0);
  }

  public User fetchUser(String username, String password){
    Object[] arg = new Object[]{username, passwordEncoder.encodePassword(password,SALT)};
    List<User> userList = jdbcTemplate.query(RETRIEVE_USER_BY_USERNAME_AND_PASSWORD, arg, userMapper());
    if(userList.size() == 0) throw new BadCredentialsException("The username or password you entered is incorrect");
    return userList.get(0);
  }

  public Boolean insertUser(User user, String password){
    Integer result = jdbcTemplate.update(INSERT_USER,
        user.getUserName(),
        passwordEncoder.encodePassword(password,SALT),
        user.getRoleVal(),
        user.getName(),
        user.getEmail()
    );
    return result == 1;

  }

  public void deleteUser(String username){
    jdbcTemplate.execute("DELETE FROM zombie_users WHERE username = '" + username + "'");
  }


  public boolean userNameExists(String username) {
    List<User> users = fetchAllUsersWithUserName(username);
    return users.size() != 0;
  }

  private RowMapper<User> userMapper() {
    return new RowMapper<User>() {
      @Override
      public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return new User(resultSet.getString("username"),
            Role.generateRole(resultSet.getString("role")),
            resultSet.getString("name"),
            resultSet.getString("email"));
      }
    };
  }

  private List<User> fetchAllUsersWithUserName(String username) {
    Object[] arg = new Object[]{username};
    return jdbcTemplate.query(RETRIEVE_USER_BY_USERNAME, arg, userMapper());
  }
}
