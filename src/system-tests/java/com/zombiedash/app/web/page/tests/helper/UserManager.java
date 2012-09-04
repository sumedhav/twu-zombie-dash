package com.zombiedash.app.web.page.tests.helper;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserManager {

    private JdbcTemplate jdbcTemplate;
    private String userName;

    public UserManager(JdbcTemplate jdbcTemplate , String userName) {
        this.jdbcTemplate = jdbcTemplate;
        this.userName = userName;
    }

    public void insertUser()
    {
        jdbcTemplate.execute(String.format(
                "insert into zombie_users values('" + userName + "','password12',1,'yahya','email@email.com')"));
    }

    public void deleteUser()
    {
        jdbcTemplate.execute("DELETE FROM zombie_users where username = '" + userName+"'");
    }
}
