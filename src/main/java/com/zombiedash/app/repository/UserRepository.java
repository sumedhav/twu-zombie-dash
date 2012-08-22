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
}
