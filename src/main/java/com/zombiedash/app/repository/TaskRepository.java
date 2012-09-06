package com.zombiedash.app.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

import static org.mockito.Matchers.anyString;

public class TaskRepository {

    private JdbcTemplate jdbcTemplate;
    private static final String INSERT_TASK = "INSERT INTO zombie_task values(?,?)";

    @Autowired
    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UUID insert(String name) {
        UUID id = UUID.randomUUID();
        jdbcTemplate.update(INSERT_TASK, name, id);
        return id;
    }
}
