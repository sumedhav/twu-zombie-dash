package com.zombiedash.app.repository;

import com.zombiedash.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {
    private JdbcTemplate jdbcTemplate;
    private static final String RETRIEVE_USER_ROW = "SELECT username, password FROM users where role = ?";
    private static final String RETRIEVE_USER_BY_USERNAME = "SELECT * FROM users where username = ?";
    private static final String RETRIEVE_ALL_USERS = "SELECT * FROM users";
    private static final String INSERT_USER = "INSERT INTO users values (?,?,?,?,?)";

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User retrieveAdminUser() {
        Object[] adminRoleArg = new Object[]{0};
        List<User> userList = jdbcTemplate.query(RETRIEVE_USER_ROW, adminRoleArg, new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {

                return new User(resultSet.getString("username"),
                        resultSet.getString("password"));
            }
        });
        return userList.get(0);
    }

    public List<User> retrieveAllUsers() {
        return jdbcTemplate.query(RETRIEVE_ALL_USERS, new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {

                return new User(resultSet.getString("username"),
                        resultSet.getString("password"));
            }
        });
    }

    public User retrieveUser(String username) {
        Object[] arg = new Object[]{username};
        List<User> userList = jdbcTemplate.query(RETRIEVE_USER_BY_USERNAME, arg, new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {

                return new User(resultSet.getString("username"),
                        resultSet.getString("password"));
            }
        });
        return userList.get(0);
    }

    public Boolean createUser(User user) {
        Integer result = jdbcTemplate.update(INSERT_USER,
                user.getUserName(),
                user.getPassword(),
                user.getRole(),
                user.getName(),
                user.getEmail()
                );
        return result == 1;

    }
}
