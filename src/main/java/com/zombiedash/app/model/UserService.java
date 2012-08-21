package com.zombiedash.app.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserService(){
    }

    public UserService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public User createAdmin() {
        String sql = "SELECT username FROM users WHERE role = ?";
        Object[] args = new Object[]{0};
        String username = jdbcTemplate.queryForObject(sql, args, String.class);
        sql = "SELECT password FROM users WHERE role = ?";
        String password = jdbcTemplate.queryForObject(sql, args, String.class);
        return new User(username,password.toCharArray());
    }
}
